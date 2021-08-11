package com.github.arthurfiorette.sinklibrary.exception;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.executor.v2.TaskContext;
import com.github.arthurfiorette.sinklibrary.logging.Level;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A simple exception handler that logs any errors. But if the error isn't a
 * {@link RuntimeException}, disable his plugin.
 */
@RequiredArgsConstructor
public class SimpleExceptionHandler implements BaseExceptionHandler {

  @Getter
  @NonNull
  private final BasePlugin basePlugin;

  @Override
  public void handle(Class<?> author, Throwable exc, String message, Object... args) {
    if (exc instanceof RuntimeException) {
      basePlugin.log(Level.ERROR, author, "Runtime exception caugth: " + message, exc);
      return;
    }

    // Disable this plugin if it isn't a runtime exception.
    basePlugin.log(Level.FATAL, author,
        "Throwable caugth: " + message + "\n Disabling this plugin.", exc);
    forceDisable();
  }

  @Override
  public void handle(Class<?> author, Throwable exc) {
    if (exc instanceof RuntimeException) {
      basePlugin.log(Level.ERROR, author, "Runtime exception caugth:", exc);
      return;
    }

    // Disable this plugin if it isn't a runtime exception.
    basePlugin.log(Level.FATAL, author, "Throwable caugth.\n Disabling this plugin.", exc);
    forceDisable();
  }

  private void forceDisable() {
    TaskContext.BUKKIT.runLater(basePlugin,
        () -> basePlugin.getPluginLoader().disablePlugin(basePlugin), 1L);
  }

}
