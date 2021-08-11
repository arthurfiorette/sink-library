package com.github.arthurfiorette.sinklibrary.exception.sink;

/**
 * A default internal class that should not be used externally. Any superclass
 * of this exception mean an incorrect use of this library.
 */
public abstract class SinkException extends RuntimeException {

  private static final long serialVersionUID = -1348572937110782641L;

  public SinkException(final String message) {
    super(
      message + "\nSee more in https://github.com/arthurfiorette/sink-library#commons-problems"
    );
  }
}
