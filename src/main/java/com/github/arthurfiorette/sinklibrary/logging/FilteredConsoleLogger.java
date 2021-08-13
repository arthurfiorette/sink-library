package com.github.arthurfiorette.sinklibrary.logging;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class FilteredConsoleLogger extends ConsoleLogger {

  @Getter
  @Setter
  @NonNull
  private Level maxLevel;

  public FilteredConsoleLogger(
    @NonNull final BasePlugin basePlugin,
    @NonNull final Level maxLevel
  ) {
    super(basePlugin);
    this.maxLevel = maxLevel;
  }

  @Override
  public void log(
    final Level level,
    final Class<?> author,
    final String message,
    final Object... args
  ) {
    if (this.maxLevel.isAtLeastAsSpecificAs(level)) {
      super.log(level, author, message, args);
    }
  }

  @Override
  public void log(
    final Level level,
    final Class<?> author,
    final String message,
    final Throwable throwable
  ) {
    if (this.maxLevel.isAtLeastAsSpecificAs(level)) {
      super.log(level, author, message, throwable);
    }
  }
}
