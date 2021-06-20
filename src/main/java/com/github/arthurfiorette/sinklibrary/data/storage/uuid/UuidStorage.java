package com.github.arthurfiorette.sinklibrary.data.storage.uuid;

import com.github.arthurfiorette.sinklibrary.data.database.JsonDatabase;
import com.github.arthurfiorette.sinklibrary.data.storage.gson.GsonStorage;
import com.github.arthurfiorette.sinklibrary.interfaces.Identifiable;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class UuidStorage<V extends Identifiable> extends GsonStorage<UUID, V> {

  public UuidStorage(JsonDatabase<UUID> database, Class<V> clazz, Executor executor) {
    super(database, clazz, executor);
  }

  public CompletableFuture<Void> save(V value) {
    return this.save(value.getUniqueId(), value);
  }

  public Void saveSync(V value) {
    return this.saveSync(value.getUniqueId(), value);
  }
}
