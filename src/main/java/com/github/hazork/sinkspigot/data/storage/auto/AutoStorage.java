package com.github.hazork.sinkspigot.data.storage.auto;

import com.github.hazork.sinkspigot.data.database.Database;
import com.github.hazork.sinkspigot.data.storage.Storage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Storage is an abstract class to better communicate with an
 * Database<{@link JsonObject}> and the external classes. this can be used in
 * many ways, like cached storage and etc...
 * <p>
 * This is an automatic serializer that uses the
 * {@link Gsons#toJsonTree(Gson, Object)} and
 * {@link Gsons#fromJson(Gson, com.google.gson.JsonElement)} to serialize and
 * deserialize the objects. This must only be used when the object CAN be auto
 * serializated.
 *
 * @param <T> the storage object type
 * 
 * @author https://github.com/Hazork/sink-library/
 */
public class AutoStorage<T> extends Storage<T> {

    private final Class<T> clazz;
    private Gson gson = new Gson();

    /**
     * Constructs a auto storage.
     * 
     * @param database the database to send and recieve information.
     * @param clazz the type class
     */
    public AutoStorage(Database database, Class<T> clazz) {
	super(database);
	this.clazz = clazz;
    }

    /**
     * Changes the gson isntance to auto serialize
     * 
     * @param gson the new gson
     */
    protected void changeGson(Gson gson) {
	this.gson = (gson == null) ? this.gson : gson;
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
