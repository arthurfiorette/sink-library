package com.github.arthurfiorette.sinklibrary.data.repository;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.*;

@RequiredArgsConstructor
public abstract class AbstractRepository<K, V> implements Repository<K, V>, BaseComponent {

  @NonNull
  protected final Database<K, V> database;

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
        this.save(key, value);
      },
      this.getBasePlugin().getExecutor()
    );
  }

  @Override
  public CompletableFuture<V> get(final K key) {
    return CompletableFuture.supplyAsync(
      () -> {
        final V value = this.database.get(key);

        if (value != null) {
          return value;
        }

        return this.create(key);
      },
      this.getBasePlugin().getExecutor()
    );
  }

  @Override
  public CompletableFuture<Collection<V>> getMany(final Set<K> keys) {
    return CompletableFuture.supplyAsync(
      () -> this.database.getMany(keys),
      this.getBasePlugin().getExecutor()
    );
  }
}
