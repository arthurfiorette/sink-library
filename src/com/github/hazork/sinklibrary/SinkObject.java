package com.github.hazork.sinklibrary;

import java.util.function.Consumer;

import com.github.hazork.sinklibrary.scheduler.SinkScheduler;

public interface SinkObject {

    SinkPlugin getPlugin();

    default SinkScheduler getScheduler() {
	return getPlugin().scheduler;
    }

    default void schedule(Consumer<SinkScheduler> scheduler) {
	scheduler.accept(getScheduler());
    }

}
