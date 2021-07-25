package com.github.arthurfiorette.sinklibrary.data.repository;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.data.storage.Storage;

/**
 * It's something between a {@link Storage} and a {@link Database}, but it has
 * its serialized and deserialized types the same.
 * <p>
 * It can be some kind of pre-processor, such as for caching or asynchronous
 * operations
 *
 * @param <K> key type
 * @param <V> value type
 */
public interface Repository<K, V> {
  CompletableFuture<Void> save(K key, V value);

  CompletableFuture<V> get(K key);

  CompletableFuture<Collection<V>> getMany(Set<K> keys);

  default void saveSync(final K key, final V value) {
    this.save(key, value).join();
  }

  default V getSync(final K key) {
    return this.get(key).join();
  }

  default Collection<V> getManySync(final Set<K> keys) {
    return this.getMany(keys).join();
  }
}
