package com.github.arthurfiorette.sinklibrary;

import com.github.arthurfiorette.sinklibrary.core.ComponentManager;
import com.github.arthurfiorette.sinklibrary.executor.TaskContext;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.logging.Level;
import org.bukkit.plugin.Plugin;

public interface BasePlugin extends Plugin {
  @Override
  void onEnable();

  @Override
  void onDisable();

  void treatThrowable(Class<?> author, Throwable exc, String message, Object... args);

  ComponentManager getManager();

  default void log(final Level level, final String msg, final Object... args) {
    this.getLogger().log(level, msg, args);
  }

  default void runAsync(final Runnable runnable) {
    TaskContext.ASYNC.run(this, runnable);
  }

  default void runSync(final Runnable runnable) {
    TaskContext.SYNC.run(this, runnable);
  }

  default <T extends BaseService> T getService(final Class<T> clazz) {
    return this.getManager().getService(clazz);
  }

  default <T extends BaseComponent> T getComponent(final Class<T> clazz) {
    return this.getManager().getComponent(clazz);
  }

  default <T> CompletableFuture<T> asyncCallback(final Supplier<T> supplier) {
    return CompletableFuture.supplyAsync(supplier, this::runAsync);
  }

  default <T> CompletableFuture<T> syncCallback(final Supplier<T> supplier) {
    return CompletableFuture.supplyAsync(supplier, this::runSync);
  }
}
