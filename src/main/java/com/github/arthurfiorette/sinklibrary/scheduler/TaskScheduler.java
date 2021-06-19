package com.github.arthurfiorette.sinklibrary.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

/**
 * A simple enum to help scheduling tasks with BukkitScheduler.
 *
 * @author https://github.com/Hazork/sink-library/
 */
public enum TaskScheduler {
  SYNC {
    @Override
    public BukkitTask run(Plugin plugin, Runnable runnable) {
      return scheduler().runTask(plugin, runnable);
    }

    @Override
    public BukkitTask run(Plugin plugin, Runnable runnable, long delay) {
      return scheduler().runTaskLater(plugin, runnable, delay);
    }

    @Override
    public BukkitTask run(Plugin plugin, Runnable runnable, long delay, long interval) {
      return scheduler().runTaskTimer(plugin, runnable, delay, interval);
    }
  },
  ASYNC {
    @Override
    public BukkitTask run(Plugin plugin, Runnable runnable) {
      return scheduler().runTaskAsynchronously(plugin, runnable);
    }

    @Override
    public BukkitTask run(Plugin plugin, Runnable runnable, long delay) {
      return scheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
    }

    @Override
    public BukkitTask run(Plugin plugin, Runnable runnable, long delay, long interval) {
      return scheduler().runTaskTimerAsynchronously(plugin, runnable, delay, interval);
    }
  };

  /**
   * Run a anything with BukkitScheduler.
   *
   * @param plugin the reference to the plugin scheduling task
   * @param runnable the task to be run
   *
   * @return the BukkitTask associated with this run.
   */
  public abstract BukkitTask run(Plugin plugin, Runnable runnable);

  /**
   * Run a anything with BukkitScheduler and a delay to execute.
   *
   * @param plugin the reference to the plugin scheduling task
   * @param runnable the task to be run
   * @param delay the delay in ticks
   *
   * @return the BukkitTask associated with this run.
   */
  public abstract BukkitTask run(Plugin plugin, Runnable runnable, long delay);

  /**
   * Run a anything with BukkitScheduler and a delay to execute and a interval
   * between runs.
   *
   * @param plugin the reference to the plugin scheduling task
   * @param runnable the task to be run
   * @param delay the delay in ticks
   * @param interval the ticks to wait between runs
   *
   * @return the BukkitTask associated with this run.
   */
  public abstract BukkitTask run(Plugin plugin, Runnable runnable, long delay, long interval);

  private static BukkitScheduler scheduler() {
    return Bukkit.getScheduler();
  }
}
