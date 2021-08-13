package com.github.arthurfiorette.sinklibrary.core;

import com.github.arthurfiorette.sinklibrary.component.Service;
import com.github.arthurfiorette.sinklibrary.component.providers.ComponentProvider;
import com.github.arthurfiorette.sinklibrary.core.SinkOptions.SinkOptionsBuilder;
import com.github.arthurfiorette.sinklibrary.exception.ExceptionHandler;
import com.github.arthurfiorette.sinklibrary.logging.BaseLogger;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class SinkPlugin extends JavaPlugin implements BasePlugin {

  @Getter
  @NonNull
  private final ComponentProvider provider;

  @Getter
  @NonNull
  private final BaseLogger baseLogger;

  @Getter
  @NonNull
  private final ExecutorService executor;

  @Getter
  @NonNull
  private final ExceptionHandler exceptionHandler;

  public SinkPlugin() {
    this(
      (self, builder) -> {
        /* Default Options */
      }
    );
  }

  /**
   * @param options a {@link BiConsumer} with itself as the first parameter so
   * you can use this while calling super() and a {@link SinkOptionsBuilder} so
   * you can change the default behavior of this plugin
   */
  public SinkPlugin(final BiConsumer<SinkPlugin, SinkOptionsBuilder> options) {
    // Create and build this plugin options.
    final SinkOptionsBuilder builder = SinkOptions.builder(this);
    options.accept(this, builder);
    final SinkOptions so = builder.build();

    // Apply his properties
    this.provider = so.getComponentProvider();
    this.baseLogger = so.getBaseLogger();
    this.executor = so.getExecutorService();
    this.exceptionHandler = so.getExceptionHandler();
  }

  /**
   * @implNote If you need to execute code on startup, implements the
   * {@link Service} class on your {@link SinkPlugin} and register it on
   * {@link SinkPlugin#components()}
   */
  @Override
  public final void onEnable() {
    this.provider.enableAll();
  }

  /**
   * @implNote If you need to execute code on startup, implements the
   * {@link Service} class on your {@link SinkPlugin} and register it on
   * {@link SinkPlugin#components()}
   */
  @Override
  public final void onDisable() {
    this.provider.disableAll();
  }
}
