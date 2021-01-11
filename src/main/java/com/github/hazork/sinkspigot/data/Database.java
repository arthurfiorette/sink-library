package com.github.hazork.sinkspigot.data;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;

/**
 * Represents a simple database model.
 *
 * @param <T> the value type to be persisted in this database
 * 
 * @author https://github.com/Hazork/sink-library/
 */
public interface Database {

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
     * @param key the value key
     * @param value the value
     */
    void save(String key, JsonObject value);

    /**
     * Returns a raw value in this database
     *
     * @param key the value key to search
     * 
     * @return the object or null if not found
     */
    JsonObject get(String key);

    /**
     * @return all entries in this database
     */
    List<JsonObject> getAll();

    /**
     * Returns all entries in this database with specified properties.
     * <p>
     * {@code getAll(obj -> obj.has("memberName");}
     * 
     * @param filter any Predicate to filter the result list.
     * 
     * @return the filtered list
     */
    default List<JsonObject> getAll(Predicate<JsonObject> filter) {
	return getAll().stream().filter(filter).collect(Collectors.toList());
    }

}
