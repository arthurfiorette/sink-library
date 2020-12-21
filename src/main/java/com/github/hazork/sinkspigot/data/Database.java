package com.github.hazork.sinkspigot.data;

import java.util.Set;

/**
 * Represents a simple database to work with Storage classes
 *
 * @param <T> the value type to be persisted in this database
 * @author https://github.com/Hazork/sink-library/
 */
public interface Database<T> {

    /**
     * Open this database connection
     */
    void open();

    /**
     * Close this database connection
     */
    void close();

    /**
     * Save a raw value in this database
     *
     * @param key   the value key
     * @param value the value
     */
    void save(String key, T value);

    /**
     * Returns a raw value in this database
     *
     * @param key the value key to search
     * @return the object or null if not found
     */
    T get(String key);

    /**
     * @return all entries in this database
     */
    Set<T> getAll();

}
