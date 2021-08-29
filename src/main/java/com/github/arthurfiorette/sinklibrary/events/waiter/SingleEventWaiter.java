package com.github.arthurfiorette.sinklibrary.events.waiter;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.events.SingleListener;
import com.github.arthurfiorette.sinklibrary.executor.TaskRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;

import lombok.NonNull;

public abstract class SingleEventWaiter<E extends Event> extends SingleListener<E> {

  public SingleEventWaiter(@NonNull Class<E> eventClass, @NonNull BasePlugin basePlugin,
      @NonNull EventPriority eventPriority) {
    super(eventClass, basePlugin, eventPriority);
  }

  public SingleEventWaiter(@NonNull Class<E> eventClass, @NonNull BasePlugin basePlugin,
      @NonNull EventPriority eventPriority, TaskRunner runner) {
    super(eventClass, basePlugin, eventPriority, runner);
  }

  private final ScheduledExecutorService schedulerExecutor = new ScheduledThreadPoolExecutor(1);
  private final List<WaitingEvent<E>> pendingEvents = new ArrayList<>();

  public CompletableFuture<E> waitEvent() {
    return this.waitingEvent(e -> true).getFuture();
  }

  public CompletableFuture<E> waitEvent(final Predicate<E> test) {
    return this.waitingEvent(test).getFuture();
  }

  public CompletableFuture<E> waitEvent(final Predicate<E> test, final long delay,
      final TimeUnit unit) {
    final WaitingEvent<E> waitingEvent = this.waitingEvent(test);
    schedulerExecutor.schedule(waitingEvent::exceptionallyTooLong, delay, unit);
    return waitingEvent.getFuture();
  }

  private WaitingEvent<E> waitingEvent(final Predicate<E> test) {
    final WaitingEvent<E> waitingEvent = new WaitingEvent<>(test, null);
    pendingEvents.add(waitingEvent);
    return waitingEvent;
  }

  @Override
  protected void handle(E event) {
    for(int i = 0; i < pendingEvents.size(); i++) {
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
