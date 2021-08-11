package com.github.arthurfiorette.sinklibrary.logging;

import com.github.arthurfiorette.sinklibrary.component.Component;

import java.util.Collection;

public interface BaseLogger extends Component {
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
