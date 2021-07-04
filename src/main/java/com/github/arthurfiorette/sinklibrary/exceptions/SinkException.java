package com.github.arthurfiorette.sinklibrary.exceptions;

public abstract class SinkException extends RuntimeException {

  private static final long serialVersionUID = -1348572937110782641L;

  public SinkException(final String message) {
    super(
      message + "\nSee more in https://github.com/arthurfiorette/simk-library#commons-problems"
    );
  }
}
