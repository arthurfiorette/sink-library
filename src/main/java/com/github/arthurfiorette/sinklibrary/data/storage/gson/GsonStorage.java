package com.github.arthurfiorette.sinklibrary.data.storage.gson;

import java.util.concurrent.Executor;
import java.util.function.Function;

import com.github.arthurfiorette.sinklibrary.data.database.JsonDatabase;
import com.github.arthurfiorette.sinklibrary.data.storage.AbstractStorage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class GsonStorage<K, V> extends AbstractStorage<K, V, JsonObject> {

  protected final Class<V> clazz;
  protected Gson gson = new Gson();
  protected Function<K, V> generator;

  public GsonStorage(
    final JsonDatabase<K> database,
    final Class<V> clazz,
    final Executor executor,
    final Function<K, V> generator
  ) {
    super(database, executor);
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
}
