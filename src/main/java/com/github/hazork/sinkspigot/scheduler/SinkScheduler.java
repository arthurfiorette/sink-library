package com.github.hazork.sinkspigot.scheduler;

import com.github.hazork.sinkspigot.SinkObject;

/**
 * A interface that helps to schedule runnables.
 * 
 * @author https://github.com/Hazork/sink-library/
 */
public interface SinkScheduler extends SinkObject {

    default void runTask(Runnable task) {
	TaskScheduler.SYNC.run(getPlugin(), task);
    }

    default void runTask(Runnable task, long delay) {
	TaskScheduler.SYNC.run(getPlugin(), task, delay);
    }

    default void runTask(Runnable task, long delay, long interval) {
	TaskScheduler.SYNC.run(getPlugin(), task, delay, interval);
    }

    default void runAsyncTask(Runnable task) {
	TaskScheduler.ASYNC.run(getPlugin(), task);
    }

    default void runAsyncTask(Runnable task, long delay) {
	TaskScheduler.ASYNC.run(getPlugin(), task, delay);
    }

    default void runAsyncTask(Runnable task, long delay, long interval) {
	TaskScheduler.ASYNC.run(getPlugin(), task, delay, interval);
    }

}
