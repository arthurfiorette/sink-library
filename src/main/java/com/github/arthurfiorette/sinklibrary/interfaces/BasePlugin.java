package com.github.arthurfiorette.sinklibrary.interfaces;

import com.github.arthurfiorette.sinklibrary.components.ComponentManager;
import com.github.arthurfiorette.sinklibrary.executor.v2.TaskContext;
import com.github.arthurfiorette.sinklibrary.services.SpigotService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public interface BasePlugin extends Plugin {
  @Override
  void onEnable();

  @Override
  void onDisable();

  void treatThrowable(Class<?> author, Throwable exc, String message, Object... args);

  ComponentManager getManager();

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

  default void log(final Level level, final String msg, final Object... args) {
    Bukkit.getConsoleSender()
        .sendMessage("[" + this.getName() + "] (" + SpigotService.colorizeLogLevel(level)
            + level.getName() + ChatColor.RESET + ") " + String.format(msg, args));
  }

  default <T extends BaseComponent> T getComponent(final Class<T> clazz) {
    return this.getManager().getComponent(clazz);
  }
}
