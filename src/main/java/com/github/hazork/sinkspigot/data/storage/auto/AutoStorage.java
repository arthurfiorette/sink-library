package com.github.hazork.sinkspigot.data.storage.auto;

import com.github.hazork.sinkspigot.data.database.Database;
import com.github.hazork.sinkspigot.data.storage.Storage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Storage is an abstract class to better communicate with an
 * {@code Database<{@link JsonObject}>} and the external classes. this can be
 * used in many ways, like cached storage and etc...
 * <p>
 * This is an automatic serializer that uses the {@link Gson} to serialize and
 * deserialize the objects. This must only be used when the object CAN be auto
 * serializated
 *
 * @param <T> the storage object type
 * 
 * @author https://github.com/Hazork/sink-library/
 */
public class AutoStorage<T> extends Storage<T> {

    private final Class<T> clazz;
    private Gson gson;

    /**
     * Constructs a auto storage.
     * 
     * @param database the database to send and recieve information.
     * @param clazz the type class
     */
    public AutoStorage(Database<JsonObject> database, Gson gson, Class<T> clazz) {
	super(database);
	this.clazz = clazz;
	this.gson = gson;
    }

    @Override
    public JsonObject serialize(T object) {
	return (JsonObject) gson.toJsonTree(object, clazz);
    }

    @Override
    public T deserialize(JsonObject json) {
	return gson.fromJson(json, clazz);
    }

}
