/**
 *
 */
package com.github.arthurfiorette.sinklibrary.exception.sink;

public class IllegalComponentException extends SinkException {

  private static final long serialVersionUID = 4437302001597736468L;

  public IllegalComponentException(final Class<?> cause) {
    super(String.format("Attempt to register a generic class: %s", cause));
  }
}
