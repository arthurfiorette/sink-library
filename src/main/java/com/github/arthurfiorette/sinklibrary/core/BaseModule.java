package com.github.arthurfiorette.sinklibrary.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.github.arthurfiorette.sinklibrary.components.ComponentManager;
import com.github.arthurfiorette.sinklibrary.executor.v2.TaskContext;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.logging.BaseLogger;
import com.github.arthurfiorette.sinklibrary.logging.Level;

public interface BaseModule {

  void treatThrowable(Object author, Throwable throwable, String message, Object... args);

  ComponentManager getManager();

  BaseLogger getBaseLogger();

  /**
   * This method is used by all code to create and execute asynchronous tasks.
   * <p>
   * Ensure that when calling
   * {@link TaskContext#runLater(BasePlugin, Runnable, long)} or
   * {@link TaskContext#runTimer(BasePlugin, Runnable, long, long)}, that this
   * executor returns an instance of {@link ScheduledExecutorService}
   * <p>
   * Defaults to {@link Executors#newScheduledThreadPool(int)} with the number
   * of processors available at runtime.
   *
   * @return the executor of this plugin
   */
  default ExecutorService getExecutor() {
    return Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
  }

  default <T extends BaseComponent> T getComponent(final Class<T> clazz) {
    return this.getManager().getComponent(clazz);
  }

  default void log(final Level level, final Object author, final String message, final Object... args) {
    this.getBaseLogger().log(level, author, message, args);
  }

  default void log(final Level level, final Object author, final String message, final Throwable throwable) {
    this.getBaseLogger().log(level, author, message, throwable);
  }

  default void log(final Level level, final String message, final Object... args) {
    this.getBaseLogger().log(level, message, args);
  }

  default void log(final Level level, final String message, final Throwable throwable) {
    this.getBaseLogger().log(level, message, throwable);
  }

}
