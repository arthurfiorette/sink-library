package com.github.arthurfiorette.sinklibrary.executor;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import lombok.NonNull;

public interface TaskRunner {
  /**
   * @param plugin the reference to the plugin scheduling task
   * @param runnable the task to be run
   */
  void run(BasePlugin plugin, @NonNull Runnable runnable);

  /**
   * @param plugin the reference to the plugin scheduling task
   * @param runnable the task to be run
   * @param delay the delay in <b>TICKS</b>
   */
  void runLater(BasePlugin plugin, Runnable runnable, long delay);

  /**
   * @param plugin the reference to the plugin scheduling task
   * @param runnable the task to be run
   * @param delay the delay in <b>TICKS</b>
   * @param interval the ticks to wait between runs
   */
  void runTimer(BasePlugin plugin, Runnable runnable, long delay, long interval);
}
