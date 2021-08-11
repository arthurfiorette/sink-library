package com.github.arthurfiorette.sinklibrary.interfaces;

/**
 * A class that can supply objects of a single type. No guarantees are implied
 * by this interface.
 */
public interface Builder<T> {

  /**
   * Retrieves an instance of the appropriate type. The returned object may or
   * may not be a new instance, depending on the implementation.
   *
   * @return an instance of the appropriate type
   */
  T build();

}
