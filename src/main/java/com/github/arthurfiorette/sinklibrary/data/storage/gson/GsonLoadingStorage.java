package com.github.arthurfiorette.sinklibrary.data.storage.gson;

import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.data.database.JsonDatabase;
import com.github.arthurfiorette.sinklibrary.data.storage.LoadingStorage;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class GsonLoadingStorage<K, V> extends LoadingStorage<K, V, JsonObject> {

  protected final Class<V> clazz;
  protected Gson gson = new Gson();
  protected final Function<K, V> generator;
  protected final BasePlugin plugin;

  public GsonLoadingStorage(
    final BasePlugin plugin,
    final JsonDatabase<K> database,
    final Class<V> clazz,
    final Executor executor,
    final UnaryOperator<CacheBuilder<Object, Object>> builder,
    final Function<K, V> generator
  ) {
    super(database, executor, builder);
    this.plugin = plugin;
    this.clazz = clazz;
    this.generator = generator;
  }

  @Override
  protected V create(final K key) {
    return this.generator.apply(key);
  }

  @Override
  public JsonObject serialize(final V object) {
    return (JsonObject) this.gson.toJsonTree(object, this.clazz);
  }

  @Override
  public V deserialize(final JsonObject raw) throws JsonSyntaxException {
    return this.gson.fromJson(raw, this.clazz);
  }

  public void setGson(final Gson gson) {
    this.gson = gson;
  }

  @Override
  public BasePlugin getPlugin() {
    return this.plugin;
  }
}
