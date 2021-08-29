package com.github.arthurfiorette.sinklibrary.events.waiter;

import com.github.arthurfiorette.sinklibrary.events.waiter.WaitingEventException.Type;

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.bukkit.event.Event;

@RequiredArgsConstructor
public class WaitingEvent<E extends Event> {

  @Getter
  private final CompletableFuture<E> future = new CompletableFuture<>();

  private final Predicate<E> test;
  private final Class<E> eventType;

  @SuppressWarnings("unchecked")
  public boolean test(final Event event) throws ClassCastException {
    return test.test((E) event);
  }

  @Synchronized("future")
  public void exceptionallyTooLong() {
    future.completeExceptionally(new WaitingEventException(Type.EVENT_WAITED_TOO_LONG, eventType));
  }

  @Synchronized("future")
  public void exceptionallyImpossible() {
    future.completeExceptionally(new WaitingEventException(Type.EVENT_NOT_POSSIBLE, eventType));
  }

  @Synchronized("future")
  @SuppressWarnings("unchecked")
  public void complete(final Event event) throws ClassCastException {
    future.complete((E) event);
  }
}
