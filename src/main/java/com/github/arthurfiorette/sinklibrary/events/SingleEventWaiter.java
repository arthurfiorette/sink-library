package com.github.arthurfiorette.sinklibrary.events;

import com.github.arthurfiorette.sinklibrary.component.Service;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.PluginManager;

@RequiredArgsConstructor
public class SingleEventWaiter<E extends Event> implements Listener, Service, EventExecutor {

  @Getter
  @NonNull
  private final BasePlugin basePlugin;

  @Getter
  @NonNull
  private final Class<E> eventClass;

  @Getter
  @NonNull
  private final EventPriority eventPriority;

  private final ScheduledExecutorService schedulerExecutor = new ScheduledThreadPoolExecutor(1);
  private final List<WaitingEvent<E>> pendingEvents = new ArrayList<>();

  @Override
  public void enable() throws Exception {
    final PluginManager pluginManager = basePlugin.getServer().getPluginManager();
    pluginManager.registerEvent(eventClass, this, eventPriority, this, basePlugin);
  }

  @Override
  public void disable() throws Exception {
    HandlerList.unregisterAll(this);
  }

  public CompletableFuture<E> wait(final Predicate<E> test) {
    return this.waitingEvent(test).getFuture();
  }

  public CompletableFuture<E> wait(final Predicate<E> test, final long delay, final TimeUnit unit) {
    final WaitingEvent<E> waitingEvent = this.waitingEvent(test);
    schedulerExecutor.schedule(waitingEvent::exceptionallyTooLong, delay, unit);
    return waitingEvent.getFuture();
  }

  private WaitingEvent<E> waitingEvent(final Predicate<E> test) {
    final WaitingEvent<E> waitingEvent = new WaitingEvent<>(test, eventClass);
    pendingEvents.add(waitingEvent);
    return waitingEvent;
  }

  @Override
  public void execute(final Listener listener, final Event event) throws EventException {
    for (int i = 0; i < pendingEvents.size(); i++) {
      final WaitingEvent<? extends Event> waitingEvent = pendingEvents.get(i);

      final boolean approved = waitingEvent.test(event);
      if (!approved) {
        continue;
      }

      pendingEvents.remove(i);
      waitingEvent.complete(event);
    }
  }
}
