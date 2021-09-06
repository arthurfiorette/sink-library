package com.github.arthurfiorette.sinklibrary.events.waiter;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;

@RequiredArgsConstructor
public class WaitingEventException extends RuntimeException {

  private static final long serialVersionUID = -901684785293644209L;

  @Getter
  @NonNull
  private final Type type;

  @Getter
  private final Class<? extends Event> event;

  public enum Type {
    EVENT_NOT_POSSIBLE,
    EVENT_WAITED_TOO_LONG
  }
}
