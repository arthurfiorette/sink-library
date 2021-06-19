package br.com.arthurfiorette.sinklibrary.data.storage;

import br.com.arthurfiorette.sinklibrary.data.database.Database;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import java.util.function.UnaryOperator;

/**
 * A cached storage is a storage type to reduce the communication with the
 * database, and only find the object for a key one time per expiration.
 * <p>
 * You can define the expiration with the {@code CacheStorage(Database,
 * UnaryOperator<CacheBuilder<Object, Object>>)} constructor because the
 * constructor with only an database doesnt remove the entry.
 * <p>
 * Is completely recommended to you don't save any T in an variable, as the
 * value can get outdated. it is more advisable to save the key in an variable,
 * as it never changes, and create a method that returns T simplifying the
 * access to {@link CacheStorage#get(String)}
 *
 * @param <T> the object type to be saved
 *
 * @author https://github.com/Hazork/sink-library/
 */
public abstract class CacheStorage<T> extends Storage<T> {

  private volatile LoadingCache<String, T> cache;

  /**
   * Constructs a storage with no condition to invalidade ant entry in the cache
   *
   * @deprecated You should provide an invalidation information for the cache
   *
   * @param database the database to send and recieve information
   */
  @Deprecated
  protected CacheStorage(Database<JsonObject> database) {
    this(database, UnaryOperator.identity());
  }

  /**
   * Constructs a storage with a specified loading cache
   *
   * @param database the database to send and recieve information
   * @param cache the custom loading cache
   */
  protected CacheStorage(Database<JsonObject> database, LoadingCache<String, T> cache) {
    super(database);
    this.cache = cache;
  }

  /**
   * Constructs a storage with specified loading cache options options. It's
   * highly recommended to define the invalidation conditions here.
   *
   * @param database the database to send and recieve information
   * @param options a unary operator that will be applied when building the
   * cache
   */
  protected CacheStorage(
    Database<JsonObject> database,
    UnaryOperator<CacheBuilder<Object, Object>> options
  ) {
    super(database);
    cache =
      options
        .apply(CacheBuilder.newBuilder())
        .removalListener(new DataListener())
        .build(new DataLoader());
  }

  @Override
  public T get(String key) {
    return cache.getUnchecked(key);
  }

  /**
   * {@inheritDoc}
   *
   * @deprecated You should let the cache auto-save any key and value
   */
  @Override
  @Deprecated
  public JsonObject save(String key, T object) {
    return super.save(key, object);
  }

  /**
   * Loads the latest value on the database to put into the cache
   *
   * @param key the object key
   */
  public void refresh(String key) {
    cache.refresh(key);
  }

  /**
   * Discards any cached value for key {@code key}
   *
   * @param key the object key
   */
  public void invalidate(String key) {
    cache.invalidate(key);
  }

  /**
   * Cleans all the keys saving it all on the database
   */
  public void cleanUp() {
    cache.cleanUp();
  }

  /**
   * @return a immutable copy of this cache
   */
  public ImmutableMap<String, T> asMap() {
    return ImmutableMap.copyOf(cache.asMap());
  }

  /**
   * @return the cache stats
   */
  public CacheStats stats() {
    return cache.stats();
  }

  /**
   * @return the cache size
   */
  public long size() {
    return cache.size();
  }

  /**
   * An inner class to save the value on the database when it is removed from
   * the cache.
   *
   * @author https://github.com/Hazork/sink-library/
   */
  public class DataListener implements RemovalListener<String, T> {

    @Override
    public void onRemoval(RemovalNotification<String, T> notification) {
      CacheStorage.super.save(notification.getKey(), notification.getValue());
    }
  }

  /**
   * An inner class to load the value on the database when it's needed.
   *
   * @author https://github.com/Hazork/sink-library/
   */
  public class DataLoader extends CacheLoader<String, T> {

    @Override
    public T load(String key) {
      return CacheStorage.super.get(key);
    }
  }
}
