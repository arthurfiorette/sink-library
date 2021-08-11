package com.github.arthurfiorette.sinklibrary.core;

import com.github.arthurfiorette.sinklibrary.components.ComponentManager;
import com.github.arthurfiorette.sinklibrary.components.SimpleComponentManager;
import com.github.arthurfiorette.sinklibrary.exception.BaseExceptionHandler;
import com.github.arthurfiorette.sinklibrary.exception.SimpleExceptionHandler;
import com.github.arthurfiorette.sinklibrary.logging.BaseLogger;
import com.github.arthurfiorette.sinklibrary.logging.BukkitLogger;
import com.github.arthurfiorette.sinklibrary.logging.Level;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Builder(builderMethodName = "builder")
public class SinkOptions {

  @Getter
  @NonNull
  private ComponentManager manager;

  @Getter
  @NonNull
  private BaseLogger baseLogger;

  @Getter
  @NonNull
  private ExecutorService executor;

  @Getter
  @NonNull
  private BaseExceptionHandler exceptionHandler;

  /**
   * Create a new builder for the provided {@link SinkPlugin}
   *
   * @param plugin the plugin owner
   *
   * @return the new builder
   */
  public static SinkOptionsBuilder builder(SinkPlugin plugin) {
    return new SinkOptionsBuilder(plugin);
  }

  /**
   * Class overrided to apply the default settings
   *
   * @author https://github.com/Hazork/sink-library/
   */
  public static class SinkOptionsBuilder {

    private SinkOptionsBuilder(SinkPlugin plugin) {
      manager(new SimpleComponentManager(plugin));
      baseLogger(new BukkitLogger(plugin, Level.ALL));
      executor(new ScheduledThreadPoolExecutor(1));
      exceptionHandler(new SimpleExceptionHandler(plugin));
    }
  }
}
