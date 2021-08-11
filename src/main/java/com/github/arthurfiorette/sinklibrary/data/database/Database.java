package com.github.arthurfiorette.sinklibrary.data.database;

import com.github.arthurfiorette.sinklibrary.component.Service;
import java.util.Collection;
import javax.annotation.Nullable;

/**
 * Represents a simple database model. <b>All of these methods are meant to be
 * executed in a synchronous way</b>
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public interface Database<K, V> extends Service {
  /**
   * Save a value in this database
   *
   * @param key the value key
   * @param value the value
   */
  void save(K key, V value);

  /**
   * Returns a value in this database
   *
   * @param key the value key to search
   *
   * @return the object or null if not found
   */
  @Nullable
  V get(K key);

  /**
   * Return all values in this database for these keys
   *
   * @param keys any string key collection
   *
   * @return the collected list
   */
  Collection<V> getMany(Collection<K> keys);
}
