package com.github.arthurfiorette.sinklibrary.data.repository;

import com.github.arthurfiorette.sinklibrary.component.Service;
import com.github.arthurfiorette.sinklibrary.data.CacheOperator;
import com.github.arthurfiorette.sinklibrary.data.LoadingWrapper;
import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.data.storage.LoadingStorage;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import lombok.Getter;
import lombok.NonNull;

public abstract class LoadingRepository<K, V>
  implements Repository<K, V>, Service, LoadingWrapper<K, V> {

  @Getter
  @NonNull
  protected final LoadingCache<K, V> cache;

  @NonNull
  protected final Database<K, V> database;

  /**
   * Constructs a storage with specified loading cache options options. It's
   * highly recommended to define the invalidation conditions here.
   * <p>
   * The removal listener and cache loader can be modified by overriding
   * {@link LoadingStorage#cacheLoader()} or
   * {@link LoadingStorage#removalListener()}
   *
   * @param database the database to send and receive information
   * @param builder a unary operator that will be applied when building the
   * cache.
   */
  protected LoadingRepository(final Database<K, V> database, final CacheOperator<K, V> builder) {
    this.database = database;
    this.cache =
      builder.withNewBuilder().removalListener(this.removalListener()).build(this.cacheLoader());
  }

  @Override
  public RemovalListener<K, V> removalListener() {
    return notification -> {
      // Save synchronously
      LoadingRepository.this.database.save(notification.getKey(), notification.getValue());
    };
  }

  @Override
  public CacheLoader<K, V> cacheLoader() {
    return new CacheLoader<K, V>() {
      @Override
      public V load(final K key) throws Exception {
        // Read synchronously
        final V value = LoadingRepository.this.database.get(key);

        if (value != null) {
          return value;
        }

        return LoadingRepository.this.create(key);
      }
    };
  }

  /**
   * Create a new entry if the database returns null for a key.
   *
   * @param key the object key
   *
   * @return the newly created object
   */
  protected abstract V create(K key);

  @Override
  public void enable() {}

  /**
   * Saves all entities in this cache to the dabatase when disabling.
   */
  @Override
  public void disable() {
    this.cache.cleanUp();
  }

  /**
   * {@inheritDoc}
   * <p>
   * Prefer {@link #get(Object)}. You should let the cache auto-save any key and
   * value pair.
   */
  @Override
  public CompletableFuture<Void> save(final K key, final V value) {
    return CompletableFuture.runAsync(
      () -> {
        this.cache.put(key, value);
      },
      getBasePlugin().getExecutor()
    );
  }

  @Override
  public CompletableFuture<V> get(final K key) {
    return CompletableFuture.supplyAsync(
      () -> this.cache.getUnchecked(key),
      getBasePlugin().getExecutor()
    );
  }

  @Override
  public CompletableFuture<Collection<V>> getMany(final Set<K> keys) {
    return CompletableFuture.supplyAsync(
      () -> {
        try {
          return Lists.newArrayList(this.cache.getAll(keys).values());
        } catch (final ExecutionException e) {
          throw new CompletionException(e);
        }
      },
      getBasePlugin().getExecutor()
    );
  }
}
