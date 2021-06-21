package com.github.arthurfiorette.sinklibrary.data.storage;

import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.executor.BukkitExecutor;
import com.github.arthurfiorette.sinklibrary.executor.TaskContext;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
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

public abstract class LoadingStorage<K, V, R> implements Storage<K, V, R> {

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
  protected LoadingStorage(Database<K, R> database, Executor executor,
      UnaryOperator<CacheBuilder<Object, Object>> builder) {
    this.database = database;
    this.executor = executor;
    this.cache = builder.apply(CacheBuilder.newBuilder()).removalListener(this.removalListener())
        .build(this.cacheLoader());
  }

  /**
   * @return this instance removal listener.
   */
  protected RemovalListener<K, V> removalListener() {
    return notification -> {
      // Save synchronously
      LoadingStorage.this.database.save(notification.getKey(), LoadingStorage.this.serialize(notification.getValue()));
    };
  }

  /**
   * @return this instance cache loader.
   */
  protected CacheLoader<K, V> cacheLoader() {
    return new CacheLoader<K, V>() {
      @Override
      public V load(K key) throws Exception {
        // Read synchronously
        return LoadingStorage.this.deserialize(LoadingStorage.this.database.get(key));
      }
    };
  }

  public Database<K, R> getDatabase() {
    return this.database;
  }

  /**
   * {@inheritDoc}
   * <p>
   * Prefer {@link #get(Object)}, you should let the cache auto-save any key and
   * value pair.
   */
  @Override
  public CompletableFuture<Void> save(K key, V value) {
    return CompletableFuture.runAsync(() -> {
      this.cache.put(key, value);
    }, this.executor);
  }

  @Override
  public CompletableFuture<V> get(K key) {
    return CompletableFuture.supplyAsync(() -> this.cache.getUnchecked(key), this.executor);
  }

  @Override
  public CompletableFuture<Collection<V>> getMany(Set<K> keys) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        return Lists.newArrayList(this.cache.getAll(keys).values());
      } catch (ExecutionException e) {
        throw new CompletionException(e);
      }
    }, this.executor);
  }

  /**
   * {@inheritDoc}
   * <p>
   * <b>This method is not cached at all. Use carefully</b>
   */
  @Override
  public CompletableFuture<Collection<V>> operation(Function<Database<K, R>, Collection<R>> func) {
    return CompletableFuture.supplyAsync(
        () -> func.apply(this.database).stream().map(this::deserialize).collect(Collectors.toList()), this.executor);
  }

  /**
   * {@inheritDoc}
   * <p>
   * <b>This method is not cached at all. Use carefully</b>
   */
  @Override
  public CompletableFuture<V> operate(Function<Database<K, R>, R> func) {
    return CompletableFuture.supplyAsync(() -> this.deserialize(func.apply(this.database)), this.executor);
  }
}
