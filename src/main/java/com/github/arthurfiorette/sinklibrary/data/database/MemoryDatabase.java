package com.github.arthurfiorette.sinklibrary.data.database;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import lombok.*;

/**
 * A memory database is a database saved in a concurrent hash map and resets
 * every time that it is reloaded or closed
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class MemoryDatabase<K, T> implements Database<K, T> {

  @Getter
  @NonNull
  protected final BasePlugin basePlugin;

  @Getter
  protected ConcurrentMap<K, T> database;

  @Getter
  protected transient boolean open = false;

  public void clear() {
    this.ensureState(true);
    this.database.clear();
  }

  @Override
  public void enable() {
    this.ensureState(true);
    this.open = true;

    if (this.database == null) {
      this.database = new ConcurrentHashMap<>();
      return;
    }

    this.database.clear();
  }

  @Override
  public void disable() {
    this.ensureState(false);
    this.open = false;

    if (this.database != null) {
      this.database.clear();
      return;
    }

    this.database = null;
  }

  @Override
  public void save(final K key, final T value) {
    this.ensureState(true);
    this.database.put(key, value);
  }

  @Override
  public T get(final K key) {
    this.ensureState(true);
    return this.database.get(key);
  }

  @Override
  public Collection<T> getMany(final Collection<K> keys) {
    this.ensureState(true);
    final List<T> list = new ArrayList<>();
    for (final K key : keys) {
      final T t = this.get(key);
      if (t != null) {
        list.add(t);
      }
    }
    return list;
  }

  private void ensureState(final boolean open) {
    if (this.open != open) {
      throw new IllegalStateException("Attempt to access the database while it wasn't open.");
    }
  }
}
