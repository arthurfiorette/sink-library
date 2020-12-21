package com.github.hazork.sinkspigot.data.storage;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.hazork.sinkspigot.data.Database;
import com.google.common.base.Verify;
import com.google.gson.JsonObject;

/**
 * Storage is an abstract class to better communicate with an
 * Database<{@link JsonObject}> and the external classes. this can be used in
 * many ways, like cached storage and etc...
 *
 * @param <V> the storage object type
 * @author https://github.com/Hazork/sink-library/
 */
public abstract class Storage<V> {

    private final Database<JsonObject> database;

    /**
     * Constructs a Storage
     *
     * @param database the JsonObject database to save any value.
     */
    public Storage(Database<JsonObject> database) {
	Verify.verifyNotNull(database);
	this.database = database;
    }

    /**
     * Serializes any V object into a JsonObject
     *
     * @param json  the empty JsonObject
     * @param value the object to serialize
     * @return the json object with its defined properties
     */
    public abstract JsonObject serialize(JsonObject json, V value);

    /**
     * Deserializes any JsonObject into a V object
     *
     * @param json the json object with their properties
     * @return the V object
     */
    public abstract V deserialize(JsonObject json);

    /**
     * Returns a V object deserialized from the database
     *
     * @param key the key name
     * @return the V object
     */
    public V get(String key) {
	JsonObject json = database.get(key);
	if (json != null) {
	    return this.deserialize(json);
	} else {
	    throw new NullPointerException("JsonDatabase#get(String) retuned null for key: " + key);
	}
    }

    /**
     * Saves a value serializing it in the database
     *
     * @param key   the key value
     * @param value the value
     * @return the value saved
     */
    public V save(String key, V value) {
	database.save(key, this.serialize(new JsonObject(), value));
	return value;
    }

    /**
     * @return all entries in the database
     */
    public Set<V> getAll() {
	return database.getAll().stream().filter(Objects::nonNull).map(this::deserialize).collect(Collectors.toSet());
    }

    /**
     * Opens the database from the storage class
     */
    public void openDatabase() {
	database.open();
    }

    /**
     * Closes the database from the storage class
     */
    public void closeDatabase() {
	database.close();
    }

    /**
     * @return the database associated with this object
     */
    public Database<JsonObject> getDatabase() {
	return database;
    }
}
