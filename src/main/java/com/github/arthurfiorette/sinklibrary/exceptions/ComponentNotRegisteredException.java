/**
 *
 */
package com.github.arthurfiorette.sinklibrary.exceptions;

public class ComponentNotRegisteredException extends SinkException {

  private static final long serialVersionUID = -4816135180933525622L;

  public ComponentNotRegisteredException(final Class<?> cause) {
    super(
      String.format(
        "Attempt to get component: %s, but it isn't registered. Maybe the registration order is wrong in your plugin.",
        cause.getSimpleName()
      )
    );
  }
}
