package com.github.arthurfiorette.sinklibrary.logging;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class BukkitLogger implements BaseLogger {

  @Getter
  @NonNull
  private final BasePlugin basePlugin;

  @Setter
  @Getter
  @NonNull
  private Level maxLevel;

  @Getter
  @NonNull
  private final ArrayList<LogFilter> filters = new ArrayList<>();

  @Override
  public int intLevel() {
    return this.maxLevel.intValue();
  }

  @Override
  public void log(
    final Level level,
    final Object author,
    final String message,
    final Object... args
  ) {
    Bukkit
      .getConsoleSender()
      .sendMessage(
        "[" +
        author.getClass().getSimpleName() +
        "] (" +
        this.format(level) +
        ChatColor.RESET +
        ") " +
        String.format(message, args)
      );
  }

  @Override
  public void log(
    final Level level,
    final Object author,
    final String message,
    final Throwable throwable
  ) {
    this.log(level, author, message);
    for (final StackTraceElement trace : throwable.getStackTrace()) {
      Bukkit.getConsoleSender().sendMessage(trace.toString());
    }
  }
}
