package com.github.hazork.sinkspigot.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
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
	    return Bukkit.getScheduler().runTask(plugin, runnable);
	}

	@Override
	public BukkitTask run(Plugin plugin, Runnable runnable, long delay) {
	    return Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
	}

	@Override
	public BukkitTask run(Plugin plugin, Runnable runnable, long delay, long interval) {
	    return Bukkit.getScheduler().runTaskTimer(plugin, runnable, delay, interval);
	}
    },
    ASYNC {

	@Override
	public BukkitTask run(Plugin plugin, Runnable runnable) {
	    return Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
	}

	@Override
	public BukkitTask run(Plugin plugin, Runnable runnable, long delay) {
	    return Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
	}

	@Override
	public BukkitTask run(Plugin plugin, Runnable runnable, long delay, long interval) {
	    return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, interval);
	}
    };

    /**
     * Run a anything with BukkitScheduler.
     *
     * @param plugin   the reference to the plugin scheduling task
     * @param runnable the task to be run
     * @return the BukkitTask associated with this run.
     */
    public abstract BukkitTask run(Plugin plugin, Runnable runnable);

    /**
     * Run a anything with BukkitScheduler and a delay to execute.
     *
     * @param plugin   the reference to the plugin scheduling task
     * @param runnable the task to be run
     * @param delay    the delay in ticks
     * @return the BukkitTask associated with this run.
     */
    public abstract BukkitTask run(Plugin plugin, Runnable runnable, long delay);

    /**
     * Run a anything with BukkitScheduler and a delay to execute and a interval
     * between runs.
     *
     * @param plugin   the reference to the plugin scheduling task
     * @param runnable the task to be run
     * @param delay    the delay in ticks
     * @param interval the ticks to wait between runs
     * @return the BukkitTask associated with this run.
     */
    public abstract BukkitTask run(Plugin plugin, Runnable runnable, long delay, long interval);
}
