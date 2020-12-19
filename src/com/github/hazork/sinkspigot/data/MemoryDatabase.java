package com.github.hazork.sinkspigot.data;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Nullable;

public class MemoryDatabase<T> implements Database<T> {

    private transient ConcurrentMap<String, T> database;

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
	checkState();
	database.put(key, value);
    }

    @Override
    @Nullable
    public T get(String key) {
	checkState();
	return database.get(key);
    }

    @Override
    public Set<T> getAll() {
	checkState();
	return new HashSet<>(database.values());
    }

    private void checkState() {
	if (database == null) {
	    throw new IllegalStateException("Attempt to access the database while it wasn't open.");
	}
    }

}
