package com.github.arthurfiorette.sinklibrary.data.storage;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.executor.BukkitExecutor;
import com.github.arthurfiorette.sinklibrary.executor.TaskContext;

public abstract class AbstractStorage<K, V, R> implements Storage<K, V, R> {

  protected Database<K, R> database;
  protected Executor executor;

  /**
   * Create a new storage with a specified database and a executor.
   * <p>
   * Using any {@link TaskContext#SYNC} executor from {@link BukkitExecutor}
   * will stop bukkit main thread while accessing any data from a database.
   *
   * @param executor this executor to be used for all asynchronous calls,
   */
  public AbstractStorage(final Database<K, R> database, final Executor executor) {
    this.executor = executor;
    this.database = database;
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
  public CompletableFuture<Void> save(final K key, final V value) {
    return CompletableFuture.runAsync(
      () -> {
        this.database.save(key, this.serialize(value));
      },
      this.executor
    );
  }

  @Override
  public CompletableFuture<V> get(final K key) {
    return CompletableFuture.supplyAsync(
      () -> {
        final R raw = this.database.get(key);
        if (raw == null) {
          return this.create(key);
        }
        return this.deserialize(raw);
      },
      this.executor
    );
  }

  @Override
  public CompletableFuture<Collection<V>> getMany(final Set<K> keys) {
    return CompletableFuture.supplyAsync(
      () -> this.database.getMany(keys)
        .stream()
        .map(this::deserialize)
        .collect(Collectors.toList()),
      this.executor
    );
  }

  @Override
  public CompletableFuture<Collection<V>> operation(final Function<Database<K, R>, Collection<R>> func) {
    return CompletableFuture.supplyAsync(
      () -> func
        .apply(this.database)
        .stream()
        .map(this::deserialize)
        .collect(Collectors.toList()),
      this.executor
    );
  }

  @Override
  public CompletableFuture<V> operate(final Function<Database<K, R>, R> func) {
    return CompletableFuture.supplyAsync(
      () -> this.deserialize(func.apply(this.database)),
      this.executor
    );
  }
}
