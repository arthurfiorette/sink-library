/**
 *
 */
package com.github.arthurfiorette.sinklibrary.exception.sink;

public class IllegalConstructorException extends SinkException {

  private static final long serialVersionUID = 4437302001597736468L;

  public IllegalConstructorException(final Class<?> cause) {
    super("Attempt to register a illegal class (" + cause.getName()
        + ") because his constructor is wrong." + "\nTo use a ConstructorLoader, Make"
        + " sure your class has a constructor with one argument that extends BasePlugin");
  }
}
