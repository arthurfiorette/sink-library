package com.github.hazork.sinkspigot.data.database;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A memory database is a database saved in a concurrent hash map and resets
 * every time that it is reloaded or closed
 *
 * @author https://github.com/Hazork/sink-library/
 */
public class MemoryDatabase<T> implements Database<T> {

    private ConcurrentMap<String, T> database;

    @Override
    public void open() {
	if (database != null) {
	    throw new IllegalStateException("Attempt to open the connection to the database but it was already open.");
	}
	database = new ConcurrentHashMap<>();
    }

    @Override
    public void close() {
	database = null;
    }

    @Override
    public void save(String key, T value) {
	this.checkState();
	database.put(key, value);
    }

    @Override
    public T get(String key) {
	this.checkState();
	return database.get(key);
    }

    @Override
    public Collection<T> getAll() {
	this.checkState();
	return database.values();
    }

    private void checkState() {
	if (database == null) {
	    throw new IllegalStateException("Attempt to access the database while it wasn't open.");
	}
    }

}
