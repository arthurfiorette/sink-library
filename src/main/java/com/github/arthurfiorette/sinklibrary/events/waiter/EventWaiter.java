package com.github.arthurfiorette.sinklibrary.events.waiter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import com.github.arthurfiorette.sinklibrary.component.Service;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;

import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.PluginManager;

import lombok.Getter;
import lombok.NonNull;

public abstract class EventWaiter implements Listener, Service, EventExecutor {

  @Getter
  @NonNull
  private final BasePlugin basePlugin;

  @Getter
  @NonNull
  private final EventPriority eventPriority;

  @Getter
  @NonNull
  private final Class<? extends Event>[] possibleEvents;

  private final ScheduledExecutorService schedulerExecutor = new ScheduledThreadPoolExecutor(1);
  private final Map<Class<? extends Event>, List<WaitingEvent<? extends Event>>> pendingEvents = new HashMap<>();

  @SafeVarargs
  public EventWaiter(
    final BasePlugin basePlugin,
    final EventPriority eventPriority,
    final Class<? extends Event>... events
  ) {
    this.basePlugin = basePlugin;
    this.eventPriority = eventPriority;
    possibleEvents = events;
  }

  @Override
  public void enable() throws Exception {
    final PluginManager pluginManager = basePlugin.getServer().getPluginManager();
    for (final Class<? extends Event> event : possibleEvents) {
      pluginManager.registerEvent(event, this, eventPriority, this, basePlugin);
    }
  }

  @Override
  public void disable() throws Exception {
    HandlerList.unregisterAll(this);
  }

  public <E extends Event> CompletableFuture<E> waitEvent(final Class<E> event) {
    return this.waitingEvent(event, e -> true).getFuture();
  }

  public <E extends Event> CompletableFuture<E> waitEvent(
    final Class<E> event,
    final Predicate<E> test
  ) {
    return this.waitingEvent(event, test).getFuture();
  }

  public <E extends Event> CompletableFuture<E> waitEvent(
    final Class<E> event,
    final Predicate<E> test,
    final long delay,
    final TimeUnit unit
  ) {
    final WaitingEvent<E> waitingEvent = this.waitingEvent(event, test);
    schedulerExecutor.schedule(waitingEvent::exceptionallyTooLong, delay, unit);
    return waitingEvent.getFuture();
  }

  private <E extends Event> WaitingEvent<E> waitingEvent(
    final Class<E> event,
    final Predicate<E> test
  ) {
    final List<WaitingEvent<? extends Event>> pendingEvent = pendingEvents.computeIfAbsent(
      event,
      key -> new ArrayList<>()
    );
    final WaitingEvent<E> waitingEvent = new WaitingEvent<>(test, event);
    pendingEvent.add(waitingEvent);

    boolean possible = false;
    for (final Class<? extends Event> possibleEvent : possibleEvents) {
      if (possibleEvent == event) {
        possible = true;
        break;
      }
    }

    // Verify that the provided event are in the possible events list
    if (!possible) {
      waitingEvent.exceptionallyImpossible();
    }

    return waitingEvent;
  }

  @Override
  public void execute(final Listener listener, final Event event) throws EventException {
    final List<WaitingEvent<? extends Event>> waitingEvents = pendingEvents.get(event.getClass());

    // Does not have events waiting for this event
    if (waitingEvents == null || waitingEvents.isEmpty()) {
      return;
    }

    for (int i = 0; i < waitingEvents.size(); i++) {
      final WaitingEvent<? extends Event> waitingEvent = waitingEvents.get(i);

      final boolean approved = waitingEvent.test(event);
      if (!approved) {
        continue;
      }

      waitingEvents.remove(i);
      waitingEvent.complete(event);
    }
  }
}
