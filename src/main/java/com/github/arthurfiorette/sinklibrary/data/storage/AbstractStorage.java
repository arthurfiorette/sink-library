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
  public AbstractStorage(Database<K, R> database, Executor executor) {
    this.executor = executor;
    this.database = database;
  }

  public Database<K, R> getDatabase() {
    return this.database;
  }

  @Override
  public CompletableFuture<Void> save(K key, V value) {
    return CompletableFuture.runAsync(() -> {
      this.database.save(key, this.serialize(value));
    }, executor);
  }

  @Override
  public CompletableFuture<V> get(K key) {
    return CompletableFuture.supplyAsync(() -> this.deserialize(database.get(key)), executor);
  }

  @Override
  public CompletableFuture<Collection<V>> getMany(Set<K> keys) {
    return CompletableFuture.supplyAsync(
        () -> database.getMany(keys).stream().map(this::deserialize).collect(Collectors.toList()), executor);
  }

  @Override
  public CompletableFuture<Collection<V>> operation(Function<Database<K, R>, Collection<R>> func) {
    return CompletableFuture
        .supplyAsync(() -> func.apply(database).stream().map(this::deserialize).collect(Collectors.toList()), executor);
  }

  /**
   * asd
   */
  @Override
  public CompletableFuture<V> operate(Function<Database<K, R>, R> func) {
    return CompletableFuture.supplyAsync(() -> this.deserialize(func.apply(database)), executor);
  }

}
