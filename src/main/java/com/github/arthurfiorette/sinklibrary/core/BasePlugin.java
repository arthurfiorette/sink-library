package com.github.arthurfiorette.sinklibrary.core;

import com.github.arthurfiorette.sinklibrary.components.ComponentManager;
import com.github.arthurfiorette.sinklibrary.exception.BaseExceptionHandler;
import com.github.arthurfiorette.sinklibrary.executor.v2.TaskContext;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.logging.BaseLogger;
import com.github.arthurfiorette.sinklibrary.logging.Level;
import java.util.concurrent.*;
import org.bukkit.plugin.Plugin;

public interface BasePlugin extends Plugin {

  ComponentManager getManager();

  BaseLogger getBaseLogger();

  /**
   * This method is used by all code to create and execute asynchronous tasks.
   * <p>
   * Ensure that when calling
   * {@link TaskContext#runLater(BasePlugin, Runnable, long)} or
   * {@link TaskContext#runTimer(BasePlugin, Runnable, long, long)}, that this
   * executor returns an instance of {@link ScheduledExecutorService}
   *
   * @return the executor of this plugin
   */
  ExecutorService getExecutor();
  
  BaseExceptionHandler getExceptionHandler();

  default <T extends BaseComponent> T getComponent(final Class<T> clazz) {
    return this.getManager().getComponent(clazz);
  }

  default void log(
    final Level level,
    final Object author,
    final String message,
    final Object... args
  ) {
    this.getBaseLogger().log(level, author, message, args);
  }

  default void log(
    final Level level,
    final Object author,
    final String message,
    final Throwable throwable
  ) {
    this.getBaseLogger().log(level, author, message, throwable);
  }

  default void log(final Level level, final String message, final Object... args) {
    this.getBaseLogger().log(level, message, args);
  }

  default void log(final Level level, final String message, final Throwable throwable) {
    this.getBaseLogger().log(level, message, throwable);
  }
}
