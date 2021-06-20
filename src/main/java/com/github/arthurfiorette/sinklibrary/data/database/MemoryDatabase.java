package com.github.arthurfiorette.sinklibrary.data.database;

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

  private ConcurrentMap<K, T> database;

  /**
   * Erase all data.
   */
  public void clear() {
    this.checkState();
    this.database = new ConcurrentHashMap<>();
  }

  @Override
  public void open() {
    this.checkState();
    this.database = new ConcurrentHashMap<>();
  }

  @Override
  public boolean isOpen() {
    return this.database != null;
  }

  @Override
  public void close() {
    this.database = null;
  }

  @Override
  public void save(K key, T value) {
    this.checkState();
    this.database.put(key, value);
  }

  @Override
  public T get(K key) {
    this.checkState();
    return this.database.get(key);
  }

  @Override
  public Collection<T> getMany(Collection<K> keys) {
    this.checkState();
    List<T> list = new ArrayList<>();
    for(K key: keys) {
      T t = this.get(key);
      if (t != null) {
        list.add(t);
      }
    }
    return list;
  }

  private void checkState() {
    if (!this.isOpen()) {
      throw new IllegalStateException("Attempt to access the database while it wasn't open.");
    }
  }
}
