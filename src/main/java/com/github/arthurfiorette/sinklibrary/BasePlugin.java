package com.github.arthurfiorette.sinklibrary;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.logging.Level;

import org.bukkit.plugin.Plugin;

import com.github.arthurfiorette.sinklibrary.executor.TaskContext;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;

public interface BasePlugin extends Plugin {

  @Override
  void onEnable();

  @Override
  void onDisable();

  void treatThrowable(Class<?> author, Throwable exc, String message, Object... args);

  void register(BaseService... services);

  /**
   * @return true if it could unregister the service
   */
  boolean unregister(Class<? extends BaseService> clazz, boolean disable);

  <T extends BaseService> T getService(Class<T> clazz);

  <T extends BaseComponent> T getComponent(Class<T> clazz);

  default void log(Level level, String msg, Object... args) {
    this.getLogger().log(level, msg, args);
  }

  default void runAsync(Runnable runnable) {
    TaskContext.ASYNC.run(this, runnable);
  }

  default void runSync(Runnable runnable) {
    TaskContext.SYNC.run(this, runnable);
  }

  default <T> CompletableFuture<T> asyncCallback(Supplier<T> supplier) {
    return CompletableFuture.supplyAsync(supplier, this::runAsync);
  }

  default <T> CompletableFuture<T> syncCallback(Supplier<T> supplier) {
    return CompletableFuture.supplyAsync(supplier, this::runSync);
  }
}
