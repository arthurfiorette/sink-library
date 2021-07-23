package com.github.arthurfiorette.sinklibrary.executor.v2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.bukkit.Bukkit;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;

public enum TaskContext {
  /**
   * Represents the synchronous context of the bukkit.
   *
   * @see Bukkit#getScheduler()
   */
  BUKKIT {
    @Override
    public void run(final BasePlugin plugin, final Runnable runnable) {
      Bukkit.getScheduler().runTask(plugin, runnable);
    }

    @Override
    public void runLater(final BasePlugin plugin, final Runnable runnable, final long delay) {
      Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
    }

    @Override
    public void runTimer(
      final BasePlugin plugin,
      final Runnable runnable,
      final long delay,
      final long interval
    ) {
      Bukkit.getScheduler().runTaskTimer(plugin, runnable, delay, interval);
    }
  },

  /**
   * Representa o contexto de execução do plugin.
   *
   * @see BasePlugin#getExecutor()
   */
  PLUGIN {
    @Override
    public void run(final BasePlugin plugin, final Runnable runnable) {
      plugin.getExecutor().execute(runnable);
    }

    @Override
    public void runLater(final BasePlugin plugin, final Runnable runnable, final long delay) {
      final ScheduledExecutorService service = TaskContext.asScheduled(plugin);
      service.schedule(runnable, TaskContext.ticksToSecond(delay), TimeUnit.SECONDS);
    }

    @Override
    public void runTimer(
      final BasePlugin plugin,
      final Runnable runnable,
      final long delay,
      final long interval
    ) {
      final ScheduledExecutorService service = TaskContext.asScheduled(plugin);
      service.scheduleAtFixedRate(
        runnable,
        TaskContext.ticksToSecond(delay),
        TaskContext.ticksToSecond(interval),
        TimeUnit.SECONDS
      );
    }
  },

  /**
   * Represents an isolated and asynchronous external execution context.
   */
  ASYNC {
    @Override
    public void run(final BasePlugin plugin, final Runnable runnable) {
      new Thread(runnable, "TaskContext.ASYNC - " + plugin.getName()).run();
    }

    @Override
    public void runLater(final BasePlugin plugin, final Runnable runnable, final long delay) {
      final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
      executor.schedule(runnable, delay * 20, TimeUnit.SECONDS);
      executor.shutdown();
    }

    @Override
    public void runTimer(
      final BasePlugin plugin,
      final Runnable runnable,
      final long delay,
      final long interval
    ) {
      final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
      executor.scheduleAtFixedRate(
        runnable,
        TaskContext.ticksToSecond(delay),
        TaskContext.ticksToSecond(interval),
        TimeUnit.SECONDS
      );
      executor.shutdown();
    }
  };

  /**
   * @param plugin the reference to the plugin scheduling task
   * @param runnable the task to be run
   *
   * @throws IllegalArgumentException if plugin is null
   * @throws IllegalArgumentException if task is null
   */
  public abstract void run(BasePlugin plugin, Runnable runnable);

  /**
   * @param plugin the reference to the plugin scheduling task
   * @param runnable the task to be run
   * @param delay the delay in <b>TICKS</b>
   *
   * @throws IllegalArgumentException if plugin is null
   * @throws IllegalArgumentException if task is null
   */
  public abstract void runLater(BasePlugin plugin, Runnable runnable, long delay);

  /**
   * @param plugin the reference to the plugin scheduling task
   * @param runnable the task to be run
   * @param delay the delay in <b>TICKS</b>
   * @param interval the ticks to wait between runs
   *
   * @throws IllegalArgumentException if plugin is null
   * @throws IllegalArgumentException if task is null
   */
  public abstract void runTimer(BasePlugin plugin, Runnable runnable, long delay, long interval);

  private static ScheduledExecutorService asScheduled(final BasePlugin plugin) {
    final ExecutorService executor = plugin.getExecutor();
    if (!(executor instanceof ScheduledExecutorService)) {
      throw new IllegalArgumentException(
        "To ran scheduled tasks, your plugin must use a java.util.concurrent.ScheduledExecutorService"
      );
    }
    return (ScheduledExecutorService) executor;
  }

  private static long ticksToSecond(final long ticks) {
    return ticks * 20;
  }
}
