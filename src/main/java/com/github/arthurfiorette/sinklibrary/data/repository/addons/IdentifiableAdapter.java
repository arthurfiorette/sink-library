package com.github.arthurfiorette.sinklibrary.data.repository.addons;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.github.arthurfiorette.sinklibrary.data.repository.Repository;
import com.github.arthurfiorette.sinklibrary.interfaces.Identifiable;

public interface IdentifiableAdapter<V extends Identifiable, R> extends Repository<UUID, V> {
  
  default CompletableFuture<Void> save(final V value) {
    return this.save(value.getUniqueId(), value);
  }

  default void saveSync(final V value) {
    this.saveSync(value.getUniqueId(), value);
  }
}
