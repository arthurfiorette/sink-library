package com.github.arthurfiorette.sinklibrary.core;

import com.github.arthurfiorette.sinklibrary.component.Component;
import com.github.arthurfiorette.sinklibrary.component.loaders.ComponentLoader;
import com.github.arthurfiorette.sinklibrary.component.providers.ComponentProvider;
import com.github.arthurfiorette.sinklibrary.exception.ExceptionHandler;
import com.github.arthurfiorette.sinklibrary.executor.TaskRunners;
import com.github.arthurfiorette.sinklibrary.logging.BaseLogger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import org.bukkit.plugin.Plugin;

public interface BasePlugin extends Plugin {
  ComponentProvider getProvider();

  BaseLogger getBaseLogger();

  /**
   * This method is used by all code to create and execute asynchronous tasks.
   * <p>
   * Ensure that when calling
   * {@link TaskRunners#runLater(BasePlugin, Runnable, long)} or
   * {@link TaskRunners#runTimer(BasePlugin, Runnable, long, long)}, that this
   * executor returns an instance of {@link ScheduledExecutorService}
   *
   * @return the executor of this plugin
   */
  ExecutorService getExecutor();

  ExceptionHandler getExceptionHandler();

  /**
   * @return the component array to register all of yours components and
   * services.
   */
  ComponentLoader[] components();

  default <T extends Component> T get(final Class<T> clazz) {
    return getProvider().get(clazz);
  }
}
