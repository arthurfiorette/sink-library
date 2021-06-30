package com.github.arthurfiorette.sinklibrary.executor;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

/**
 * A helper enum to run schedule task with {@link BukkitScheduler}
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public enum TaskContext {
  /**
   * This context means that his execution is the Bukkit main thread.
   */
  SYNC {
    @Override
    public BukkitTask run(final Plugin plugin, final Runnable runnable) {
      return Bukkit.getScheduler().runTask(plugin, runnable);
    }

    @Override
    public BukkitTask runLater(final Plugin plugin, final Runnable runnable, final long delay) {
      return Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
    }

    @Override
    public BukkitTask runTimer(final Plugin plugin, final Runnable runnable, final long delay, final long interval) {
      return Bukkit.getScheduler().runTaskTimer(plugin, runnable, delay, interval);
    }
  },
  /**
   * This context means that his execution is outside the Bukkit main thread.
   */
  ASYNC {
    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * {@inheritDoc}
     */
    @Override
    public BukkitTask run(final Plugin plugin, final Runnable runnable) {
      return Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * {@inheritDoc}
     */
    @Override
    public BukkitTask runLater(final Plugin plugin, final Runnable runnable, final long delay) {
      return Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * {@inheritDoc}
     */
    @Override
    public BukkitTask runTimer(final Plugin plugin, final Runnable runnable, final long delay, final long interval) {
      return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, interval);
    }
  };

  /**
   * Run a anything with BukkitScheduler.
   *
   * @param plugin the reference to the plugin scheduling task
   * @param runnable the task to be run
   *
   * @return the BukkitTask associated with this run.
   *
   * @throws IllegalArgumentException if plugin is null
   * @throws IllegalArgumentException if task is null
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
   *
   * @throws IllegalArgumentException if plugin is null
   * @throws IllegalArgumentException if task is null
   */
  public abstract BukkitTask runLater(Plugin plugin, Runnable runnable, long delay);

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
   *
   * @throws IllegalArgumentException if plugin is null
   * @throws IllegalArgumentException if task is null
   */
  public abstract BukkitTask runTimer(Plugin plugin, Runnable runnable, long delay, long interval);
}
