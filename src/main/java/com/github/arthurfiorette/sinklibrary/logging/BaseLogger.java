package com.github.arthurfiorette.sinklibrary.logging;

import com.github.arthurfiorette.sinklibrary.component.Component;

public interface BaseLogger extends Component {

  void log(final Level level, final Class<?> author, final String message, final Object... args);

  void log(final Level level, final Class<?> author, final String message, final Throwable throwable);

  default void log(final Level level, final String message, final Object... args) {
    this.log(level, this.getBasePlugin().getClass(), message, args);
  }

  default void log(final Level level, final String message, final Throwable throwable) {
    this.log(level, this.getBasePlugin().getClass(), message, throwable);
  }

  //

  default String format(final Level level) {
    return level.toColorUpperCase();
  }

  //

  default void fatal(final Class<?> author, final String message, final Object... args) {
    this.log(Level.FATAL, author, message, args);
  }

  default void fatal(final Class<?> author, final String message, final Throwable throwable) {
    this.log(Level.FATAL, author, message, throwable);
  }

  default void fatal(final String message, final Object... args) {
    this.log(Level.FATAL, this.getBasePlugin().getClass(), message, args);
  }

  default void fatal(final String message, final Throwable throwable) {
    this.log(Level.FATAL, this.getBasePlugin().getClass(), message, throwable);
  }

  //

  default void error(final Class<?> author, final String message, final Object... args) {
    this.log(Level.ERROR, author, message, args);
  }

  default void error(final Class<?> author, final String message, final Throwable throwable) {
    this.log(Level.ERROR, author, message, throwable);
  }

  default void error(final String message, final Object... args) {
    this.log(Level.ERROR, this.getBasePlugin().getClass(), message, args);
  }

  default void error(final String message, final Throwable throwable) {
    this.log(Level.ERROR, this.getBasePlugin().getClass(), message, throwable);
  }

  //

  default void warn(final Class<?> author, final String message, final Object... args) {
    this.log(Level.WARN, author, message, args);
  }

  default void warn(final Class<?> author, final String message, final Throwable throwable) {
    this.log(Level.WARN, author, message, throwable);
  }

  default void warn(final String message, final Object... args) {
    this.log(Level.WARN, this.getBasePlugin().getClass(), message, args);
  }

  default void warn(final String message, final Throwable throwable) {
    this.log(Level.WARN, this.getBasePlugin().getClass(), message, throwable);
  }

  //

  default void info(final Class<?> author, final String message, final Object... args) {
    this.log(Level.INFO, author, message, args);
  }

  default void info(final Class<?> author, final String message, final Throwable throwable) {
    this.log(Level.INFO, author, message, throwable);
  }

  default void info(final String message, final Object... args) {
    this.log(Level.INFO, this.getBasePlugin().getClass(), message, args);
  }

  default void info(final String message, final Throwable throwable) {
    this.log(Level.INFO, this.getBasePlugin().getClass(), message, throwable);
  }

  //

  default void debug(final Class<?> author, final String message, final Object... args) {
    this.log(Level.DEBUG, author, message, args);
  }

  default void debug(final Class<?> author, final String message, final Throwable throwable) {
    this.log(Level.DEBUG, author, message, throwable);
  }

  default void debug(final String message, final Object... args) {
    this.log(Level.DEBUG, this.getBasePlugin().getClass(), message, args);
  }

  default void debug(final String message, final Throwable throwable) {
    this.log(Level.DEBUG, this.getBasePlugin().getClass(), message, throwable);
  }

  //

  default void trace(final Class<?> author, final String message, final Object... args) {
    this.log(Level.TRACE, author, message, args);
  }

  default void trace(final Class<?> author, final String message, final Throwable throwable) {
    this.log(Level.TRACE, author, message, throwable);
  }

  default void trace(final String message, final Object... args) {
    this.log(Level.TRACE, this.getBasePlugin().getClass(), message, args);
  }

  default void trace(final String message, final Throwable throwable) {
    this.log(Level.TRACE, this.getBasePlugin().getClass(), message, throwable);
  }
}
