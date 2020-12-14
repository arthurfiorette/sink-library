package com.github.hazork.sinklibrary.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public enum TaskScheduler {

    SYNC {
	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable) {
	    return scheduler.runTask(plugin, runnable);
	}

	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable, long delay) {
	    return scheduler.runTaskLater(plugin, runnable, delay);
	}

	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable, long delay, long interval) {
	    return scheduler.runTaskTimer(plugin, runnable, delay, interval);
	}
    },
    ASYNC {

	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable) {
	    return scheduler.runTaskAsynchronously(plugin, runnable);
	}

	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable, long delay) {
	    return scheduler.runTaskLaterAsynchronously(plugin, runnable, delay);
	}

	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable, long delay, long interval) {
	    return scheduler.runTaskTimerAsynchronously(plugin, runnable, delay, interval);
	}
    };

    private static final BukkitScheduler scheduler = Bukkit.getScheduler();

    public abstract BukkitTask runTask(Plugin plugin, Runnable runnable);

    public abstract BukkitTask runTask(Plugin plugin, Runnable runnable, long delay);

    public abstract BukkitTask runTask(Plugin plugin, Runnable runnable, long delay, long interval);
}
