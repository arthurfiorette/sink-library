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
@Builder(builderMethodName = "builder", builderClassName = "Builder")
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
  public static Builder builder(final SinkPlugin plugin) {
    return new Builder(plugin);
  }

  @FunctionalInterface
  public interface Consumer {
    void customize(Builder builder);
  }
  
  /**
   * Class overrided to apply the default settings
   *
   * @author https://github.com/Hazork/sink-library/
   */
  public static class Builder {

    private Builder(final BasePlugin plugin) {
      componentProvider(new SimpleComponentProvider(plugin));
      baseLogger(new ConsoleLogger(plugin));
      executorService(new ScheduledThreadPoolExecutor(1));
      exceptionHandler(new SimpleExceptionHandler(plugin));
    }
  }
}
