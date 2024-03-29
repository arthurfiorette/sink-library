package com.github.arthurfiorette.sinklibrary.exception;

import com.github.arthurfiorette.sinklibrary.component.Component;

public interface ExceptionHandler extends Component {
  void handle(
    final Class<?> author,
    final Throwable exc,
    final String message,
    final Object... args
  );

  void handle(final Class<?> author, final Throwable exc);

  //

  default void handle(
    final Object author,
    final Throwable exc,
    final String message,
    final Object... args
  ) {
    this.handle(author.getClass(), exc, message, args);
  }

  default void handle(final Object author, final Throwable exc) {
    this.handle(author.getClass(), exc);
  }

  default void handle(final Throwable exc, final String message, final Object... args) {
    this.handle(getBasePlugin().getClass(), exc, message, args);
  }

  default void handle(final Throwable exc) {
    this.handle(getBasePlugin().getClass(), exc);
  }
}
