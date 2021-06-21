package com.github.arthurfiorette.sinklibrary.data.storage.gson;

import com.github.arthurfiorette.sinklibrary.data.database.JsonDatabase;
import com.github.arthurfiorette.sinklibrary.data.storage.AbstractStorage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.concurrent.Executor;
import java.util.function.Function;

public class GsonStorage<K, V> extends AbstractStorage<K, V, JsonObject> {

  protected final Class<V> clazz;
  protected Gson gson = new Gson();
  protected Function<K, V> generator;

  public GsonStorage(
    JsonDatabase<K> database,
    Class<V> clazz,
    Executor executor,
    Function<K, V> generator
  ) {
    super(database, executor);
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
