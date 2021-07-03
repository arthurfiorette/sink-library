/**
 * 
 */
package com.github.arthurfiorette.sinklibrary.exceptions;

public class IllegalComponentException extends SinkException {

  private static final long serialVersionUID = 4437302001597736468L;

  public IllegalComponentException(Class<?> cause) {
    super("Attemp to register a generic class");
  }

}
