package com.github.arthurfiorette.sinklibrary.core;

import com.github.arthurfiorette.sinklibrary.components.ComponentManager;
import com.github.arthurfiorette.sinklibrary.components.SimpleComponentManager;
import com.github.arthurfiorette.sinklibrary.executor.v2.TaskContext;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import com.github.arthurfiorette.sinklibrary.interfaces.ComponentLoader;
import com.github.arthurfiorette.sinklibrary.logging.BaseLogger;
import com.github.arthurfiorette.sinklibrary.logging.BukkitLogger;
import com.github.arthurfiorette.sinklibrary.logging.Level;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public abstract class SinkPlugin extends JavaPlugin implements BasePlugin {

  @Getter
  @NonNull
  private final ComponentManager manager;

  @Getter
  @NonNull
  private final BaseLogger baseLogger;

  public SinkPlugin() {
    this.manager = new SimpleComponentManager(this);
    this.baseLogger =
      new BukkitLogger(this, com.github.arthurfiorette.sinklibrary.logging.Level.ALL);
  }

  /**
   * @return the component array to register all of yours components and
   * services.
   */
  public abstract ComponentLoader[] components();

  /**
   * If you need to execute code on startup, implements the {@link BaseService}
   * class on your {@link SinkPlugin} and register it on
   * {@link SinkPlugin#components()}
   */
  @Override
  public final void onEnable() {
    this.manager.enableServices();
  }

  /**
   * If you need to execute code on startup, implements the {@link BaseService}
   * class on your {@link SinkPlugin} and register it on
   * {@link SinkPlugin#components()}
   */
  @Override
  public final void onDisable() {
    this.manager.disableServices();
  }

  @Override
  public void treatThrowable(
    final Object author,
    final Throwable exc,
    final String message,
    final Object... args
  ) {
    if (exc instanceof RuntimeException) {
      this.log(Level.ERROR, author, "Exception caugth", exc);
      return;
    }

    // Disable this plugin if it isn't a runtime exception.

    this.log(Level.FATAL, author, "Exception caugth, disabling this plugin.", exc);
    TaskContext.BUKKIT.runLater(this, () -> this.getPluginLoader().disablePlugin(this), 1L);
  }
}
