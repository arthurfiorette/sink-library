package com.github.arthurfiorette.sinklibrary.core;

import com.github.arthurfiorette.sinklibrary.component.providers.ComponentProvider;
import com.github.arthurfiorette.sinklibrary.component.providers.SimpleComponentProvider;
import com.github.arthurfiorette.sinklibrary.exception.ExceptionHandler;
import com.github.arthurfiorette.sinklibrary.exception.SimpleExceptionHandler;
import com.github.arthurfiorette.sinklibrary.logging.BaseLogger;
import com.github.arthurfiorette.sinklibrary.logging.ConsoleLogger;
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
  private ComponentProvider componentProvider;

  @Getter
  @NonNull
  private BaseLogger baseLogger;

  @Getter
  @NonNull
  private ExecutorService executorService;

  @Getter
  @NonNull
  private ExceptionHandler exceptionHandler;

  /**
   * Create a new builder for the provided {@link SinkPlugin}
   *
   * @param plugin the plugin owner
   *
   * @return the new builder
   */
  public static SinkOptionsBuilder builder(final SinkPlugin plugin) {
    return new SinkOptionsBuilder(plugin);
  }

  /**
   * Class overrided to apply the default settings
   *
   * @author https://github.com/Hazork/sink-library/
   */
  public static class SinkOptionsBuilder {

    private SinkOptionsBuilder(final BasePlugin plugin) {
      this.componentProvider(new SimpleComponentProvider(plugin));
      this.baseLogger(new ConsoleLogger(plugin));
      this.executorService(new ScheduledThreadPoolExecutor(1));
      this.exceptionHandler(new SimpleExceptionHandler(plugin));
    }
  }
}
