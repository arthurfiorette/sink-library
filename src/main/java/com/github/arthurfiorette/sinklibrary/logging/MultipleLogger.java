package com.github.arthurfiorette.sinklibrary.logging;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.google.common.collect.Sets;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class MultipleLogger implements BaseLogger {

  @Getter
  @NonNull
  private final BasePlugin basePlugin;

  @Getter
  @Setter
  private Set<CommandSender> senders = Sets.newHashSet(Bukkit.getConsoleSender());

  @Override
  public void log(final Level level, final Class<?> author, final String message,
      final Object... args) {
    this.senders.stream().forEach(sender -> {
      sender.sendMessage(BaseLogger.format(level, author, message, args));
    });
  }

  @Override
  public void log(final Level level, final Class<?> author, final String message, final Throwable throwable) {
    this.senders.stream().forEach(sender -> {
      sender.sendMessage(BaseLogger.format(level, author, message));
      for(final StackTraceElement trace: throwable.getStackTrace()) {
        sender.sendMessage(trace.toString());
      }
    });
  }
}
