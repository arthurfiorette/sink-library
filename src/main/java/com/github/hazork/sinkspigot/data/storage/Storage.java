package com.github.hazork.sinkspigot.data.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.github.hazork.sinkspigot.data.database.Database;
import com.github.hazork.sinkspigot.data.serializer.Serializable;
import com.google.gson.JsonObject;

/**
 * Storage is an abstract class to better communicate with an
 * Database<{@link JsonObject}> and the external classes. this can be used in
 * many ways, like cached storage and etc...
 *
 * @param <T> the storage object type
 * 
 * @author https://github.com/Hazork/sink-library/
 */
public abstract class Storage<T> implements Serializable<T, JsonObject> {

    protected final Database database;

    /**
     * Constructs a storage.
     * 
     * @param database the database to send and recieve information.
     */
    protected Storage(Database database) {
	this.database = database;
    }

    /**
     * Returns the deserialized object for this key
     * 
     * @param key the object key
     * 
     * @return the deserialized object
     */
    public T get(String key) {
	JsonObject jsonObject = database.get(key);
	return jsonObject == null ? null : this.deserialize(jsonObject);
    }

    /**
     * Saves the object serializing it in the database.
     * 
     * @param key the object key
     * @param object the object to save
     * 
     * @return the saved object
     */
    public JsonObject save(String key, T object) {
	JsonObject jsonObject = this.serialize(object);
	database.save(key, jsonObject);
	return jsonObject;
    }

    /**
     * Returns a list with all values in the database.
     * 
     * @param filter the filter to return a more specific and correct collection
     * 
     * @return the filtered collection
     */
    public Collection<T> getAll(Predicate<T> filter) {
	return database.getAll().stream().filter(Objects::nonNull).map(this::deserialize).filter(filter)
		.collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Opens the database connection using {@link Database#open()}
     */
    public void open() {
	database.open();
    }

    /**
     * Closes the database connectiong using {@link Database#close()}
     */
    public void close() {
	database.close();
    }

}
