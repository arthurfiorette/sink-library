package com.github.hazork.sinkspigot.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public enum TaskScheduler {

    SYNC {
	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable) {
	    return Bukkit.getScheduler().runTask(plugin, runnable);
	}

	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable, long delay) {
	    return Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
	}

	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable, long delay, long interval) {
	    return Bukkit.getScheduler().runTaskTimer(plugin, runnable, delay, interval);
	}
    },
    ASYNC {

	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable) {
	    return Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
	}

	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable, long delay) {
	    return Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
	}

	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable, long delay, long interval) {
	    return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, interval);
	}
    };

    public abstract BukkitTask runTask(Plugin plugin, Runnable runnable);

    public abstract BukkitTask runTask(Plugin plugin, Runnable runnable, long delay);

    public abstract BukkitTask runTask(Plugin plugin, Runnable runnable, long delay, long interval);
}
