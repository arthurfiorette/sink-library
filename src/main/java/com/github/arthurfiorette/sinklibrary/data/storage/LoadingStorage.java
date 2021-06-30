package com.github.arthurfiorette.sinklibrary.data.storage;

import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.executor.BukkitExecutor;
import com.github.arthurfiorette.sinklibrary.executor.TaskContext;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
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
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public abstract class LoadingStorage<K, V, R> implements Storage<K, V, R>, BaseService {

  protected final LoadingCache<K, V> cache;
  protected final Database<K, R> database;
  protected final Executor executor;

  /**
   * Constructs a storage with specified loading cache options options. It's
   * highly recommended to define the invalidation conditions here.
   * <p>
   * Using any {@link TaskContext#SYNC} executor from {@link BukkitExecutor}
   * will stop bukkit main thread while accessing any data from a database.
   * <p>
   * The removal listener and cache loader can be modified by overriding
   * {@link LoadingStorage#cacheLoader()} or
   * {@link LoadingStorage#removalListener()}
   *
   * @param database the database to send and receive information
   * @param executor this executor to be used for all asynchronous calls,
   * @param options a unary operator that will be applied when building the
   * cache.
   */
  protected LoadingStorage(
    final Database<K, R> database,
    final Executor executor,
    final UnaryOperator<CacheBuilder<Object, Object>> builder
  ) {
    this.database = database;
    this.executor = executor;
    this.cache =
      builder
        .apply(CacheBuilder.newBuilder())
        .removalListener(this.removalListener())
        .build(this.cacheLoader());
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
      this.executor
    );
  }

  @Override
  public CompletableFuture<V> get(final K key) {
    return CompletableFuture.supplyAsync(() -> this.cache.getUnchecked(key), this.executor);
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
      this.executor
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
      this.executor
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
      this.executor
    );
  }

  /**
   * @see {@link Cache#stats()}
   */
  public CacheStats stats() {
    return this.cache.stats();
  }

  /**
   * @see {@link Cache#refresh()}
   */
  public void refresh(final K key) {
    this.cache.refresh(key);
  }

  /**
   * @see {@link Cache#invalidate()}
   */
  public void invalidate(final K key) {
    this.cache.invalidate(key);
  }

  /**
   * @see {@link Cache#size()}
   */
  public long size() {
    return this.cache.size();
  }
}
