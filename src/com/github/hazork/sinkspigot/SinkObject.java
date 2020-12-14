package com.github.hazork.sinkspigot;

import java.util.function.Consumer;

import com.github.hazork.sinkspigot.scheduler.SinkScheduler;

public interface SinkObject {

    SinkPlugin getPlugin();

    default SinkScheduler getScheduler() {
	return getPlugin().scheduler;
    }

    default void schedule(Consumer<SinkScheduler> scheduler) {
	scheduler.accept(getScheduler());
    }

}
