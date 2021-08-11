package com.github.arthurfiorette.sinklibrary.data.storage.gson;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.data.CacheOperator;
import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.data.storage.LoadingStorage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.function.Function;
import lombok.Getter;
import lombok.NonNull;

public class GsonLoadingStorage<K, V> extends LoadingStorage<K, V, JsonObject> {

  @NonNull
  protected final Class<V> clazz;

  @Getter
  @NonNull
  protected final Function<K, V> generator;

  @Getter
  @NonNull
  protected final BasePlugin basePlugin;

  @Getter
  @NonNull
  protected Gson gson = new Gson();

  public GsonLoadingStorage(
    final BasePlugin plugin,
    final Database<K, JsonObject> database,
    final Class<V> clazz,
    final Function<K, V> generator,
    final CacheOperator<K, V> builder
  ) {
    super(database, builder);
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
