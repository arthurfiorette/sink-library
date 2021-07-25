package com.github.arthurfiorette.sinklibrary.data.storage;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import com.github.arthurfiorette.sinklibrary.data.database.Database;

/**
 * @param <K> Storage key type
 * @param <V> Storage value type
 * @param <R> Database raw type
 *
 * @author https://github.com/arthurfiorette/sink-library/
 */
public interface Storage<K, V, R> {

  CompletableFuture<Void> save(K key, V value);

  CompletableFuture<V> get(K key);

  CompletableFuture<Collection<V>> getMany(Set<K> keys);

  CompletableFuture<Collection<V>> operation(Function<Database<K, R>, Collection<R>> func);

  CompletableFuture<V> operate(Function<Database<K, R>, R> func);

  R serialize(V object);

  V deserialize(R raw);

  default void saveSync(final K key, final V value) {
    this.save(key, value).join();
  }

  default V getSync(final K key) {
    return this.get(key).join();
  }

  default Collection<V> getManySync(final Set<K> keys) {
    return this.getMany(keys).join();
  }

  default Collection<V> operationSync(final Function<Database<K, R>, Collection<R>> func) {
    return this.operation(func).join();
  }

  default V operateSync(final Function<Database<K, R>, R> func) {
    return this.operate(func).join();
  }

}
