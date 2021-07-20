package com.github.arthurfiorette.sinklibrary.data.storage;

import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
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
import lombok.NonNull;

public abstract class LoadingStorage<K, V, R> implements Storage<K, V, R>, BaseService {

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

  /**
   * @return this instance removal listener.
   */
  protected RemovalListener<K, V> removalListener() {
    return notification -> {
      // Save synchronously
      LoadingStorage.this.database.save(
          notification.getKey(),
          LoadingStorage.this.serialize(notification.getValue())
        );
    };
  }

  /**
   * @return this instance cache loader.
   */
  protected CacheLoader<K, V> cacheLoader() {
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
  public void enable() throws Exception {}

  /**
   * Saves all entities in this cache to the dabatase when disabling.
   */
  @Override
  public void disable() throws Exception {
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
      this.getBasePlugin().getExecutor()
    );
  }

  @Override
  public CompletableFuture<V> get(final K key) {
    return CompletableFuture.supplyAsync(
      () -> this.cache.getUnchecked(key),
      this.getBasePlugin().getExecutor()
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
      this.getBasePlugin().getExecutor()
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
      this.getBasePlugin().getExecutor()
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
      () -> this.deserialize(func.apply(this.database)),
      this.getBasePlugin().getExecutor()
    );
  }

  /**
   * Returns a current snapshot of this cache's cumulative statistics. All stats
   * are initialized to zero, and are monotonically increasing over the lifetime
   * of the cache.
   *
   * @return the cache stats
   */
  public CacheStats stats() {
    return this.cache.stats();
  }

  /**
   * Loads a new value for key {@code key}, possibly asynchronously. While the
   * new value is loading the previous value (if any) will continue to be
   * returned by {@code get(key)} unless it is evicted. If the new value is
   * loaded successfully it will replace the previous value in the cache; if an
   * exception is thrown while refreshing the previous value will remain, <i>and
   * the exception will be logged (using {@link java.util.logging.Logger}) and
   * swallowed</i>.
   * <p>
   * Caches loaded by a {@link CacheLoader} will call {@link CacheLoader#reload}
   * if the cache currently contains a value for {@code key}, and
   * {@link CacheLoader#load} otherwise. Loading is asynchronous only if
   * {@link CacheLoader#reload} was overridden with an asynchronous
   * implementation.
   * <p>
   * Returns without doing anything if another thread is currently loading the
   * value for {@code key}. If the cache loader associated with this cache
   * performs refresh asynchronously then this method may return before refresh
   * completes.
   *
   * @param key the key to be refreshed
   *
   * @since 11.0
   */
  public void refresh(final K key) {
    this.cache.refresh(key);
  }

  /**
   * Discards any cached value for key {@code key}.
   *
   * @param key the key to be invalidated
   */
  public void invalidate(final K key) {
    this.cache.invalidate(key);
  }

  /**
   * @return the approximate number of entries in this cache.
   */
  public long size() {
    return this.cache.size();
  }
}
