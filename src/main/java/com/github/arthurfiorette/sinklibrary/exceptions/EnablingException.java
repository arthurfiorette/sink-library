package com.github.arthurfiorette.sinklibrary.exceptions;

import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import lombok.NoArgsConstructor;

/**
 * A simple class to wrap any exception thrown while enabling a
 * {@link BaseService}
 */
@NoArgsConstructor
@lombok.Generated
public class EnablingException extends RuntimeException {

  private static final long serialVersionUID = -908936539462914980L;

  public EnablingException(final String message) {
    super(message);
  }

  public EnablingException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public EnablingException(final Throwable cause) {
    super(cause);
  }

  protected EnablingException(
    final String message,
    final Throwable cause,
    final boolean enableSuppression,
    final boolean writableStackTrace
  ) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  @Override
  public String toString() {
    final String s = this.getCause().getClass().getSimpleName();
    final String message = this.getLocalizedMessage();
    return (message != null) ? (s + ": " + message) : s;
  }
}
