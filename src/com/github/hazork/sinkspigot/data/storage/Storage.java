package com.github.hazork.sinkspigot.data.storage;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.hazork.sinkspigot.data.StorageDatabase;
import com.google.common.base.Verify;
import com.google.gson.JsonObject;

public abstract class Storage<V> {

    private final StorageDatabase database;

    public Storage(StorageDatabase database) {
	Verify.verifyNotNull(database);
	this.database = database;
    }

    public abstract JsonObject serialize(JsonObject json, V value);

    public abstract V deserialize(JsonObject json);

    public V get(String key) {
	JsonObject json = database.get(key);
	if (json != null) {
	    return deserialize(json);
	} else {
	    throw new NullPointerException("JsonDatabase#get(String) retuned null for key: " + key);
	}
    }

    public V save(String key, V value) {
	database.save(key, serialize(new JsonObject(), value));
	return value;
    }

    public Set<V> getAll() {
	return database.getAll().stream().filter(Objects::nonNull).map(this::deserialize).collect(Collectors.toSet());
    }

    public void openDatabase() {
	database.open();
    }

    public void closeDatabase() {
	database.close();
    }

    public StorageDatabase getDatabase() {
	return database;
    }
}
