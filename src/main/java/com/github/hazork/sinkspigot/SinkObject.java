package com.github.hazork.sinkspigot;

/**
 * A class that implement this means that his instances need a SinkPlugin to
 * work well.
 * 
 * @author https://github.com/Hazork/sink-library/
 */
public interface SinkObject {

    /**
     * @return the {@link SinkPlugin} instance involved with this instance.
     */
    SinkPlugin getPlugin();

}
