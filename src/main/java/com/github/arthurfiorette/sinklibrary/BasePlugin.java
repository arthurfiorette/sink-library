package com.github.arthurfiorette.sinklibrary;

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

  default void log(Level level, String msg, Object... args) {
    this.getLogger().log(level, msg, args);
  }

  /**
   * Register a service to this plugin. <b>Every service must have a different
   * class from each other.</b>
   *
   * @param services
   *
   * @return himself for method chaining
   */
  BasePlugin register(BaseService... services);

  /**
   * @return true if it could unregister the service
   */
  boolean unregister(Class<? extends BaseService> clazz, boolean disable);

  <T extends BaseService> T getService(Class<T> clazz);

  void runAsync(Runnable runnable);

  void runSync(Runnable runnable);

  <T> CompletableFuture<T> asyncCallback(Supplier<T> supplier);

  <T> CompletableFuture<T> syncCallback(Supplier<T> supplier);
}
