package com.github.arthurfiorette.sinklibrary.data;

import com.google.common.cache.*;

public interface LoadingWrapper<K, V> {

  LoadingCache<K, V> getCache();

  /**
   * Return a independent removal listener for this wrapper.
   * 
   * @return this instance removal listener.
   */
  RemovalListener<K, V> removalListener();

  /**
   * Return a independent cache loder for this wrapper.
   * 
   * @return this instance cache loader.
   */
  CacheLoader<K, V> cacheLoader();

  /**
   * Returns a current snapshot of this cache's cumulative statistics. All stats
   * are initialized to zero, and are monotonically increasing over the lifetime
   * of the cache.
   *
   * @return the cache stats
   */
  default CacheStats stats() {
    return this.getCache().stats();
  }

  /**
   * Loads a new value for key {@code key}, possibly asynchronously. While the
   * new value is loading the previous value (if any) will continue to be
   * returned by {@code get(key)} unless it is evicted. If the new value is
   * loaded successfully it will replace the previous value in the cache; if an
   * exception is thrown while refreshing the previous value will remain, <i>and
   * the exception will be logged (using {@link java.util.logging.Logger}) and
   * swallowed</i>.
   * <p>
   * Caches loaded by a {@link CacheLoader} will call {@link CacheLoader#reload}
   * if the cache currently contains a value for {@code key}, and
   * {@link CacheLoader#load} otherwise. Loading is asynchronous only if
   * {@link CacheLoader#reload} was overridden with an asynchronous
   * implementation.
   * <p>
   * Returns without doing anything if another thread is currently loading the
   * value for {@code key}. If the cache loader associated with this cache
   * performs refresh asynchronously then this method may return before refresh
   * completes.
   *
   * @param key the key to be refreshed
   *
   * @since 11.0
   */
  default void refresh(final K key) {
    this.getCache().refresh(key);
  }

  /**
   * Discards any cached value for key {@code key}.
   *
   * @param key the key to be invalidated
   */
  default void invalidate(final K key) {
    this.getCache().invalidate(key);
  }

  /**
   * @return the approximate number of entries in this cache.
   */
  default long size() {
    return this.getCache().size();
  }
}
