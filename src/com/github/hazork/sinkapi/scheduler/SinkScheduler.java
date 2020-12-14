package com.github.hazork.sinkapi.scheduler;

import com.github.hazork.sinkapi.SinkObject;
import com.github.hazork.sinkapi.SinkPlugin;

public class SinkScheduler implements SinkObject {

    private final SinkPlugin plugin;

    public SinkScheduler(SinkPlugin plugin) {
	this.plugin = plugin;
    }

    public void runTask(Runnable task) {
	TaskScheduler.SYNC.runTask(plugin, task);
    }

    public void runTask(Runnable task, long delay) {
	TaskScheduler.SYNC.runTask(plugin, task, delay);
    }

    public void runTask(Runnable task, long delay, long interval) {
	TaskScheduler.SYNC.runTask(plugin, task, delay, interval);
    }

    public void runAsyncTask(Runnable task) {
	TaskScheduler.ASYNC.runTask(plugin, task);
    }

    public void runAsyncTask(Runnable task, long delay) {
	TaskScheduler.ASYNC.runTask(plugin, task, delay);
    }

    public void runAsyncTask(Runnable task, long delay, long interval) {
	TaskScheduler.ASYNC.runTask(plugin, task, delay, interval);
    }

    @Override
    public SinkPlugin getPlugin() {
	return plugin;
    }
}
