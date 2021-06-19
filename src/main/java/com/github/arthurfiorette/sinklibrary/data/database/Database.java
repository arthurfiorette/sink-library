package com.github.arthurfiorette.sinklibrary.data.database;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents a simple database model.
 *
 * @author https://github.com/Hazork/sink-library/
 */
public interface Database<T> {
  /**
   * Open this database connection
   */
  void open();

  /**
   * Close this database connection
   */
  void close();

  /**
   * Save a value in this database
   *
   * @param key the value key
   * @param value the value
   */
  void save(String key, T value);

  /**
   * Returns a value in this database
   *
   * @param key the value key to search
   *
   * @return the object or null if not found
   */
  T get(String key);

  /**
   * @return all entries in this database
   */
  Collection<T> getAll();

  /**
   * Returns all entries in this database with specified properties.
   *
   * @param filter any Predicate to filter the result list.
   *
   * @return the filtered list
   */
  default Collection<T> getAll(Predicate<T> filter) {
    return this.getAll().stream().filter(filter).collect(Collectors.toList());
  }
}
