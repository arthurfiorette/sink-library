package com.github.hazork.sinkspigot.data.storage.auto;

import java.util.function.UnaryOperator;

import com.github.hazork.sinkspigot.data.database.Database;
import com.github.hazork.sinkspigot.data.storage.CacheStorage;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * A cached storage is a storage type to reduce the communication with the
 * database, and only find the object for a key one time per expiration.
 * <p>
 * This is an automatic serializer that uses the {@link Gson} to serialize and
 * deserialize the objects. This must only be used when the object CAN be auto
 * serializated.
 * <p>
 * You can define the expiration with the {@Code AutoCacheStorage(Database,
 * UnaryOperator<CacheBuilder<Object, Object>>)} constructor because the
 * constructor with only an database doesnt remove the entry.
 * <p>
 * Is completely recommended to you don't save any T in an variable, as the
 * value can get outdated. it is more advisable to save the key in an variable,
 * as it never changes, and create a method that returns T simplifying the
 * access to {@link AutoCacheStorage#get(String)}
 * 
 * @param <T> the object type to be saved
 * 
 * @author https://github.com/Hazork/sink-library/
 */
public class AutoCacheStorage<T> extends CacheStorage<T> {
    private final Class<T> clazz;
    private Gson gson = new Gson();

    /**
     * {@inheritDoc}
     * 
     * @param clazz the type class
     */
    @Deprecated
    public AutoCacheStorage(Database database, Class<T> clazz) {
	super(database);
	this.clazz = clazz;
    }

    /**
     * Constructs a storage with a specified loading cache
     * 
     * @param database the database to send and recieve information
     * @param clazz the type class
     * @param cache the custom loading cache
     */
    public AutoCacheStorage(Database database, Class<T> clazz, LoadingCache<String, T> cache) {
	super(database, cache);
	this.clazz = clazz;
    }

    /**
     * Constructs a storage with specified loading cache options options. It's
     * highly recommended to define the invalidation conditions here.
     * 
     * @param database the database to send and recieve information
     * @param clazz the type class
     * @param options a unary operator that will be applied when building the
     * cache
     */
    public AutoCacheStorage(Database database, Class<T> clazz, UnaryOperator<CacheBuilder<Object, Object>> options) {
	super(database, options);
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
