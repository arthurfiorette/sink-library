package com.github.hazork.sinkspigot;

import java.util.logging.Level;

import com.github.hazork.sinkspigot.config.YMLContainer;
import com.github.hazork.sinkspigot.scheduler.SinkScheduler;

public interface SinkHelper extends SinkScheduler {

    default void log(Level level, String msg, Object... args) {
	getPlugin().log(level, msg, args);
    }

    default void treatException(Class<?> author, Exception exc, String message, Object... args) {
	getPlugin().treatException(author, exc, message, args);
    }

    default YMLContainer getYmlContainer() {
	return getPlugin().ymlContainer;
    }

}
