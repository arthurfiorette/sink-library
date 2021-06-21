package com.github.arthurfiorette.sinklibrary.data.storage.gson;

import com.github.arthurfiorette.sinklibrary.data.database.JsonDatabase;
import com.github.arthurfiorette.sinklibrary.data.storage.LoadingStorage;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class GsonLoadingStorage<K, V> extends LoadingStorage<K, V, JsonObject> {

  protected final Class<V> clazz;
  protected Gson gson = new Gson();
  private Function<K, V> generator;

  public GsonLoadingStorage(
    JsonDatabase<K> database,
    Class<V> clazz,
    Executor executor,
    UnaryOperator<CacheBuilder<Object, Object>> builder,
    Function<K, V> generator
  ) {
    super(database, executor, builder);
    this.clazz = clazz;
    this.generator = generator;
  }

  @Override
  protected V create(K key) {
    return generator.apply(key);
  }

  @Override
  public JsonObject serialize(V object) {
    return (JsonObject) this.gson.toJsonTree(object, this.clazz);
  }

  @Override
  public V deserialize(JsonObject raw) throws JsonSyntaxException {
    return this.gson.fromJson(raw, this.clazz);
  }

  public void setGson(Gson gson) {
    this.gson = gson;
  }
}
