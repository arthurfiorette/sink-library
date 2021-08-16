package com.github.arthurfiorette.sinklibrary.data.storage;

import com.github.arthurfiorette.sinklibrary.component.Component;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.data.database.Database;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractStorage<K, V, R> implements Storage<K, V, R>, Component {

  @NonNull
  protected final Database<K, R> database;

  @Getter
  @NonNull
  protected final BasePlugin basePlugin;

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
        this.database.save(key, serialize(value));
      },
      this.getBasePlugin().getExecutor()
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
        return deserialize(raw);
      },
      this.getBasePlugin().getExecutor()
    );
  }

  @Override
  public CompletableFuture<Collection<V>> getMany(final Set<K> keys) {
    return CompletableFuture.supplyAsync(
      () ->
        this.database.getMany(keys).stream().map(this::deserialize).collect(Collectors.toList()),
      this.getBasePlugin().getExecutor()
    );
  }

  @Override
  public CompletableFuture<Collection<V>> operation(
    final Function<Database<K, R>, Collection<R>> func
  ) {
    return CompletableFuture.supplyAsync(
      () -> func.apply(this.database).stream().map(this::deserialize).collect(Collectors.toList()),
      this.getBasePlugin().getExecutor()
    );
  }

  @Override
  public CompletableFuture<V> operate(final Function<Database<K, R>, R> func) {
    return CompletableFuture.supplyAsync(
      () -> deserialize(func.apply(this.database)),
      this.getBasePlugin().getExecutor()
    );
  }
}
