package com.github.arthurfiorette.sinklibrary.logging;

import java.util.Collection;

import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;

public interface BaseLogger extends BaseComponent {
  void log(Level level, Object author, String message, Object... args);

  void log(Level level, Object author, String message, Throwable throwable);

  int intLevel();

  Collection<LogFilter> getFilters();

  default boolean addFilter(final LogFilter filter) {
    return this.getFilters().add(filter);
  }

  default boolean removeFilter(final LogFilter filter) {
    return this.getFilters().remove(filter);
  }

  default void log(final Level level, final String message, final Object... args) {
    this.log(level, this.getBasePlugin(), message, args);
  }

  default void log(final Level level, final String message, final Throwable throwable) {
    this.log(level, this.getBasePlugin(), message, throwable);
  }

  default boolean canLog(final Level level) {
    return level.intValue() <= this.intLevel();
  }

  default String format(final Level level) {
    return level.toColorUpperCase();
  }
}
