package com.github.hazork.sinkspigot.data.storage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.github.hazork.sinkspigot.data.Database;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.gson.JsonObject;

public abstract class CachedStorage<V> extends Storage<V> {

    private volatile LoadingCache<String, V> cache;

    /**
     * Constructs a CachedStorage with a specified cache implementation
     *
     * @param database the JsonObject database to save any value.
     * @param cache    the custom loading cache
     */
    public CachedStorage(Database<JsonObject> database, LoadingCache<String, V> cache) {
	super(database);
	this.cache = cache;
    }

    /**
     * Constructs a CachedStorage
     *
     * @param database the JsonObject database to save any value
     */
    public CachedStorage(Database<JsonObject> database) {
	super(database);
	cache = CacheBuilder.newBuilder().removalListener(new DataListener()).build(new DataLoader());
    }

    @Override
    public V get(String key) {
	return cache.getUnchecked(key);
    }

    /**
     * Cleans the key saving it on the database.
     *
     * @param key the object key
     */
    public void refresh(String key) {
	cache.refresh(key);
    }

    /**
     * Cleans all the keys saving it all on the database.
     */
    public void cleanUp() {
	cache.cleanUp();
    }

    /**
     * @return the cache as a map, any modification in this map changes also the
     *         cache
     */
    public ConcurrentMap<String, V> asMap() {
	return cache.asMap();
    }

    /**
     * @return the cache as a map, any modification in this map don't changes
     *         the cache
     */
    public ConcurrentMap<String, V> asMapCopy() {
	return new ConcurrentHashMap<>(cache.asMap());
    }

    /**
     * @return this cache stats
     */
    public CacheStats stats() {
	return cache.stats();
    }

    /**
     * @return the cache size
     */
    public long size() {
	return cache.size();
    }

    /**
     * An inner class to save the value on the database when it is removed from
     * the cache.
     *
     * @author https://github.com/Hazork/sink-library/
     */
    public final class DataListener implements RemovalListener<String, V> {

	@Override
	public void onRemoval(RemovalNotification<String, V> notification) {
	    CachedStorage.this.save(notification.getKey(), notification.getValue());
	}
    }

    /**
     * An inner class to load the value on the database when it's needed.
     *
     * @author https://github.com/Hazork/sink-library/
     */
    public final class DataLoader extends CacheLoader<String, V> {

	@Override
	public V load(String key) {
	    return CachedStorage.super.get(key);
	}
    }
}
