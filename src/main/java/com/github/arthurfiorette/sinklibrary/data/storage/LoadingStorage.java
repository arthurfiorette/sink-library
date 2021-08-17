package com.github.arthurfiorette.sinklibrary.data.storage;

import com.github.arthurfiorette.sinklibrary.component.Service;
import com.github.arthurfiorette.sinklibrary.data.CacheOperator;
import com.github.arthurfiorette.sinklibrary.data.LoadingWrapper;
import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NonNull;

public abstract class LoadingStorage<K, V, R>
  implements Storage<K, V, R>, Service, LoadingWrapper<K, V> {

  @Getter
  @NonNull
  protected final LoadingCache<K, V> cache;

  @NonNull
  protected final Database<K, R> database;

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
  protected LoadingStorage(final Database<K, R> database, final CacheOperator<K, V> builder) {
    this.database = database;
    this.cache =
      builder.withNewBuilder().removalListener(this.removalListener()).build(this.cacheLoader());
  }

  @Override
  public RemovalListener<K, V> removalListener() {
    return notification -> {
      // Save synchronously
      LoadingStorage.this.database.save(
          notification.getKey(),
          LoadingStorage.this.serialize(notification.getValue())
        );
    };
  }

  @Override
  public CacheLoader<K, V> cacheLoader() {
    return new CacheLoader<K, V>() {
      @Override
      public V load(final K key) throws Exception {
        // Read synchronously
        final R raw = LoadingStorage.this.database.get(key);
        if (raw == null) {
          return LoadingStorage.this.create(key);
        }
        return LoadingStorage.this.deserialize(raw);
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
   * Prefer {@link #get(Object)}, you should let the cache auto-save any key and
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

  /**
   * {@inheritDoc}
   * <p>
   * <b>This method is not cached at all. Use carefully</b>
   */
  @Override
  public CompletableFuture<Collection<V>> operation(
    final Function<Database<K, R>, Collection<R>> func
  ) {
    return CompletableFuture.supplyAsync(
      () -> func.apply(this.database).stream().map(this::deserialize).collect(Collectors.toList()),
      getBasePlugin().getExecutor()
    );
  }

  /**
   * {@inheritDoc}
   * <p>
   * <b>This method is not cached at all. Use carefully</b>
   */
  @Override
  public CompletableFuture<V> operate(final Function<Database<K, R>, R> func) {
    return CompletableFuture.supplyAsync(
      () -> deserialize(func.apply(this.database)),
      getBasePlugin().getExecutor()
    );
  }
}
