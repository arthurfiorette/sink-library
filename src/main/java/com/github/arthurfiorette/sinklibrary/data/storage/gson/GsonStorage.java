package com.github.arthurfiorette.sinklibrary.data.storage.gson;

import java.util.concurrent.Executor;
import java.util.function.Function;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.data.storage.AbstractStorage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class GsonStorage<K, V> extends AbstractStorage<K, V, JsonObject> {

  @NonNull
  protected final Class<V> clazz;
  
  @Getter
  @NonNull
  protected final BasePlugin basePlugin;

  @Getter
  @Setter
  protected Gson gson = new Gson();

  @Getter
  @NonNull
  protected Function<K, V> generator;

  public GsonStorage(
    final BasePlugin plugin,
    final Database<K, JsonObject> database,
    final Class<V> clazz,
    final Executor executor,
    final Function<K, V> generator
  ) {
    super(database, executor);
    this.basePlugin = plugin;
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

}
