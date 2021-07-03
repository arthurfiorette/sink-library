package com.github.arthurfiorette.sinklibrary.exceptions;

public abstract class SinkException extends RuntimeException {

  private static final long serialVersionUID = 2945601044667455870L;

  public SinkException(String message) {
    super(
      message + "\nSee more in https://github.com/arthurfiorette/simk-library#commons-problems"
    );
  }
}
