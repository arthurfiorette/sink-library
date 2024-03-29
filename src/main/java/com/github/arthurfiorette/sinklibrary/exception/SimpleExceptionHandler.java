package com.github.arthurfiorette.sinklibrary.exception;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.executor.TaskRunners;
import com.github.arthurfiorette.sinklibrary.logging.BaseLogger;
import com.github.arthurfiorette.sinklibrary.logging.Level;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A simple exception handler that logs any errors. But if the error isn't a
 * {@link RuntimeException}, disable his plugin.
 */
@RequiredArgsConstructor
public class SimpleExceptionHandler implements ExceptionHandler {

  @Getter
  @NonNull
  private final BasePlugin basePlugin;

  @Override
  public void handle(
    final Class<?> author,
    final Throwable exc,
    final String message,
    final Object... args
  ) {
    final BaseLogger logger = basePlugin.getBaseLogger();

    if (exc instanceof RuntimeException) {
      logger.log(Level.ERROR, author, "Runtime exception caugth: " + message, exc);
      return;
    }

    // Disable this plugin if it isn't a runtime exception.
    logger.log(
      Level.FATAL,
      author,
      "Throwable caugth: " + message + "\n Disabling this plugin.",
      exc
    );
    forceDisable();
  }

  @Override
  public void handle(final Class<?> author, final Throwable exc) {
    final BaseLogger logger = basePlugin.getBaseLogger();

    if (exc instanceof RuntimeException) {
      logger.log(Level.ERROR, author, "Runtime exception caugth:", exc);
      return;
    }

    // Disable this plugin if it isn't a runtime exception.
    logger.log(Level.FATAL, author, "Throwable caugth.\n Disabling this plugin.", exc);
    forceDisable();
  }

  private void forceDisable() {
    TaskRunners.BUKKIT.runLater(
      basePlugin,
      () -> basePlugin.getPluginLoader().disablePlugin(basePlugin),
      1L
    );
  }
}
