package com.github.arthurfiorette.sinklibrary.data.storage.gson;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.data.storage.AbstractStorage;
import com.google.gson.*;
import java.util.function.Function;
import lombok.*;

public class GsonStorage<K, V> extends AbstractStorage<K, V, JsonObject> {

  @NonNull
  protected final Class<V> clazz;

  @Getter
  @Setter
  protected Gson gson = new Gson();

  @Getter
  @NonNull
  protected Function<K, V> generator;

  public GsonStorage(
    final BasePlugin basePlugin,
    final Database<K, JsonObject> database,
    final Class<V> clazz,
    final Function<K, V> generator
  ) {
    super(database, basePlugin);
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
