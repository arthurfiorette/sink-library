package com.github.arthurfiorette.sinklibrary.data.storage.gson;

import java.util.concurrent.Executor;

import com.github.arthurfiorette.sinklibrary.data.database.JsonDatabase;
import com.github.arthurfiorette.sinklibrary.data.storage.AbstractStorage;
import com.github.arthurfiorette.sinklibrary.executor.BukkitExecutor;
import com.github.arthurfiorette.sinklibrary.plugin.BasePlugin;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class GsonStorage<K, V> extends AbstractStorage<K, V, JsonObject> {

  private final Class<V> clazz;
  private Gson gson = new Gson();

  public GsonStorage(JsonDatabase<K> database, Class<V> clazz, Executor executor) {
    super(database, executor);
    this.clazz = clazz;
  }

  /**
   * Create and simple Gson storage, that serialize and deserialize
   * automatically with {@link Gson}.
   * 
   * @param database the database to load and save values
   * @param clazz the entity class
   * @param plugin the plugin owner
   */
  public GsonStorage(JsonDatabase<K> database, Class<V> clazz, BasePlugin plugin) {
    this(database, clazz, BukkitExecutor.newAsyncSingleThreadExecutor(plugin));
  }

  @Override
  public JsonObject serialize(V object) {
    return (JsonObject) gson.toJsonTree(object, clazz);
  }

  @Override
  public V deserialize(JsonObject raw) throws JsonSyntaxException {
    return gson.fromJson(raw, clazz);
  }

  public void setGson(Gson gson) {
    this.gson = gson;
  }

}
