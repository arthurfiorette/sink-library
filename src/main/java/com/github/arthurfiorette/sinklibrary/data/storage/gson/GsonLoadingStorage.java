package com.github.arthurfiorette.sinklibrary.data.storage.gson;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.data.database.JsonDatabase;
import com.github.arthurfiorette.sinklibrary.data.storage.LoadingStorage;
import com.github.arthurfiorette.sinklibrary.executor.BukkitExecutor;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class GsonLoadingStorage<K, V> extends LoadingStorage<K, V, JsonObject> {

  private final Class<V> clazz;
  private Gson gson = new Gson();

  public GsonLoadingStorage(JsonDatabase<K> database, Class<V> clazz, Executor executor,
      UnaryOperator<CacheBuilder<Object, Object>> builder) {
    super(database, executor, builder);
    this.clazz = clazz;
  }

  /**
   * Create and simple Gson storage, that serialize and deserialize
   * automatically with {@link Gson}. You can explicitly set cache options with
   * {@link GsonLoadingStorage#GsonLoadingStorage(Database, Executor, UnaryOperator)}.
   * Default values fallback to expire after 5 minutes from access and has a
   * maximum size of 256 entities.
   * 
   * @param database the database to load and save values
   * @param clazz the entity class
   * @param plugin the plugin owner
   */
  public GsonLoadingStorage(JsonDatabase<K> database, Class<V> clazz, BasePlugin plugin) {
    this(database, clazz, BukkitExecutor.newAsyncSingleThreadExecutor(plugin),
        b -> b.expireAfterAccess(5, TimeUnit.MINUTES).maximumSize(256));
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
