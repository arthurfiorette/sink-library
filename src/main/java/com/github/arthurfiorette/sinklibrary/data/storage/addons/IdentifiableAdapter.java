package com.github.arthurfiorette.sinklibrary.data.storage.addons;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.github.arthurfiorette.sinklibrary.data.storage.Storage;
import com.github.arthurfiorette.sinklibrary.interfaces.Identifiable;

public interface IdentifiableAdapter<V extends Identifiable, R> extends Storage<UUID, V, R> {
  default CompletableFuture<Void> save(final V value) {
    return this.save(value.getUniqueId(), value);
  }

  default void saveSync(final V value) {
    this.saveSync(value.getUniqueId(), value);
  }
}
