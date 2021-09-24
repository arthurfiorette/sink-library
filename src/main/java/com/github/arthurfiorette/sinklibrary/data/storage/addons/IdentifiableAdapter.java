package com.github.arthurfiorette.sinklibrary.data.storage.addons;

import com.github.arthurfiorette.sinklibrary.data.storage.Storage;
import com.github.arthurfiorette.sinklibrary.interfaces.Identifiable;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IdentifiableAdapter<V extends Identifiable, R>
  extends Storage<UUID, V, R> {
  default CompletableFuture<Void> save(final V value) {
    return this.save(value.getUniqueId(), value);
  }

  default void saveSync(final V value) {
    this.saveSync(value.getUniqueId(), value);
  }
}
