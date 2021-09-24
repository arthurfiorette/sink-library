package com.github.arthurfiorette.sinklibrary.executor;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.util.bukkit.TickUnit;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.bukkit.Bukkit;

public enum TaskRunners implements TaskRunner {
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
    public void runLater(
      final BasePlugin plugin,
      final Runnable runnable,
      final long delay
    ) {
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
    public void runLater(
      final BasePlugin plugin,
      final Runnable runnable,
      final long delay
    ) {
      final ScheduledExecutorService service = TaskRunners.getScheduledExecutor(plugin);
      service.schedule(
        runnable,
        TickUnit.from(TimeUnit.MILLISECONDS, delay),
        TimeUnit.SECONDS
      );
    }

    @Override
    public void runTimer(
      final BasePlugin plugin,
      final Runnable runnable,
      final long delay,
      final long interval
    ) {
      final ScheduledExecutorService service = TaskRunners.getScheduledExecutor(plugin);
      service.scheduleAtFixedRate(
        runnable,
        TickUnit.from(TimeUnit.MILLISECONDS, delay),
        TickUnit.from(TimeUnit.SECONDS, interval),
        TimeUnit.SECONDS
      );
    }
  },

  /**
   * Represents an isolated and asynchronous external execution context.
   */
  ASYNC {
    /**
     * @param plugin <b>Not used. can be null</b>
     * @param runnable the task to be run
     *
     * @throws NullPointerException if the task is null
     */
    @Override
    public void run(final BasePlugin plugin, final Runnable runnable) {
      new Thread(
        runnable,
        "TaskContext.ASYNC - " + plugin == null ? "Unknown" : plugin.getName()
      )
        .run();
    }

    /**
     * @param plugin <b>Not used. can be null</b>
     * @param runnable the task to be run
     * @param delay the delay in <b>TICKS</b>
     *
     * @throws NullPointerException if the task is null
     */
    @Override
    public void runLater(
      final BasePlugin plugin,
      final Runnable runnable,
      final long delay
    ) {
      new Timer()
        .schedule(
          new TimerTask() {
            @Override
            public void run() {
              runnable.run();
            }
          },
          TickUnit.from(TimeUnit.MILLISECONDS, delay)
        );
    }

    /**
     * @param plugin <b>Not used. can be null</b>
     * @param runnable the task to be run
     * @param delay the delay in <b>TICKS</b>
     * @param interval the ticks to wait between runs
     */
    @Override
    public void runTimer(
      final BasePlugin plugin,
      final Runnable runnable,
      final long delay,
      final long interval
    ) {
      new Timer()
        .scheduleAtFixedRate(
          new RunnableTimerTask(runnable),
          TickUnit.from(TimeUnit.MILLISECONDS, delay),
          TickUnit.from(TimeUnit.MILLISECONDS, interval)
        );
    }
  };

  private static ScheduledExecutorService getScheduledExecutor(final BasePlugin plugin) {
    final ExecutorService executor = plugin.getExecutor();
    if (!(executor instanceof ScheduledExecutorService)) {
      throw new IllegalArgumentException(
        "To ran scheduled tasks, your plugin must use a java.util.concurrent.ScheduledExecutorService"
      );
    }
    return (ScheduledExecutorService) executor;
  }
}
