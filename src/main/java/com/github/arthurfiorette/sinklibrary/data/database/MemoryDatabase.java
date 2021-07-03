package com.github.arthurfiorette.sinklibrary.data.database;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A memory database is a database saved in a concurrent hash map and resets
 * every time that it is reloaded or closed
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public class MemoryDatabase<K, T> implements Database<K, T> {

  protected final BasePlugin plugin;
  protected ConcurrentMap<K, T> database;

  public MemoryDatabase(final BasePlugin plugin) {
    this.plugin = plugin;
  }

  /**
   * Erase all data.
   */
  public void clear() {
    this.checkState();
    this.database = new ConcurrentHashMap<>();
  }

  @Override
  public void enable() {
    this.checkState();
    this.database = new ConcurrentHashMap<>();
  }

  public boolean isOpen() {
    return this.database != null;
  }

  @Override
  public void disable() {
    this.database = null;
  }

  @Override
  public void save(final K key, final T value) {
    this.checkState();
    this.database.put(key, value);
  }

  @Override
  public T get(final K key) {
    this.checkState();
    return this.database.get(key);
  }

  @Override
  public Collection<T> getMany(final Collection<K> keys) {
    this.checkState();
    final List<T> list = new ArrayList<>();
    for (final K key : keys) {
      final T t = this.get(key);
      if (t != null) {
        list.add(t);
      }
    }
    return list;
  }

  @Override
  public BasePlugin getBasePlugin() {
    return this.plugin;
  }

  private void checkState() {
    if (!this.isOpen()) {
      throw new IllegalStateException("Attempt to access the database while it wasn't open.");
    }
  }
}
