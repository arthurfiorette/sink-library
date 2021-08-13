package com.github.arthurfiorette.sinklibrary.logging;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;

import org.bukkit.Bukkit;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsoleLogger implements BaseLogger {

  @Getter
  @NonNull
  private final BasePlugin basePlugin;

  @Override
  public void log(
    final Level level,
    final Class<?> author,
    final String message,
    final Object... args
  ) {
    Bukkit
      .getConsoleSender()
      .sendMessage(BaseLogger.format(level, author, message, args));
  }

  @Override
  public void log(final Level level, final Class<?> author, final String message, final Throwable throwable) {
    this.log(level, author, message);
    for (final StackTraceElement trace : throwable.getStackTrace()) {
      Bukkit.getConsoleSender().sendMessage(trace.toString());
    }
  }
}
