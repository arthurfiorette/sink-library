package com.github.hazork.sinkspigot.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.gson.JsonObject;

/**
 * A memory database is a database saved in a concurrent hash map and resets
 * every time that it is reloaded or closed
 *
 * @param <T> the value type to be saved in this database
 * 
 * @author https://github.com/Hazork/sink-library/
 */
public class MemoryDatabase implements Database {

    private transient ConcurrentMap<String, JsonObject> database;

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
    public void save(String key, JsonObject value) {
	this.checkState();
	database.put(key, value);
    }

    @Override
    public JsonObject get(String key) {
	this.checkState();
	return database.get(key);
    }

    @Override
    public List<JsonObject> getAll() {
	this.checkState();
	return new ArrayList<>(database.values());
    }

    private void checkState() {
	if (database == null) {
	    throw new IllegalStateException("Attempt to access the database while it wasn't open.");
	}
    }

}
