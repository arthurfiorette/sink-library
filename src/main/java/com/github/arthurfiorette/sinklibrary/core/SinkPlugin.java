package com.github.arthurfiorette.sinklibrary.core;

import com.github.arthurfiorette.sinklibrary.components.ComponentManager;
import com.github.arthurfiorette.sinklibrary.core.SinkOptions.SinkOptionsBuilder;
import com.github.arthurfiorette.sinklibrary.exception.BaseExceptionHandler;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import com.github.arthurfiorette.sinklibrary.interfaces.ComponentLoader;
import com.github.arthurfiorette.sinklibrary.logging.BaseLogger;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import lombok.NonNull;

public abstract class SinkPlugin extends JavaPlugin implements BasePlugin {

  @Getter
  @NonNull
  private final ComponentManager manager;

  @Getter
  @NonNull
  private final BaseLogger baseLogger;

  @Getter
  @NonNull
  private final ExecutorService executor;

  @Getter
  @NonNull
  private final BaseExceptionHandler exceptionHandler;

  public SinkPlugin() {
    this((options) -> { /* Default Options */ });
  }

  public SinkPlugin(Consumer<SinkOptionsBuilder> options) {
    // Create and build this plugin options.
    final SinkOptionsBuilder builder = SinkOptions.builder(this);
    options.accept(builder);
    SinkOptions so = builder.build();

    // Apply his properties
    this.manager = so.getManager();
    this.baseLogger = so.getBaseLogger();
    this.executor = so.getExecutor();
    this.exceptionHandler = so.getExceptionHandler();
  }

  /**
   * @return the component array to register all of yours components and
   * services.
   */
  public abstract ComponentLoader[] components();

  /**
   * @implNote If you need to execute code on startup, implements the
   * {@link BaseService} class on your {@link SinkPlugin} and register it on
   * {@link SinkPlugin#components()}
   */
  @Override
  public final void onEnable() {
    this.manager.enableServices();
  }

  /**
   * @implNote If you need to execute code on startup, implements the
   * {@link BaseService} class on your {@link SinkPlugin} and register it on
   * {@link SinkPlugin#components()}
   */
  @Override
  public final void onDisable() {
    this.manager.disableServices();
  }
}
