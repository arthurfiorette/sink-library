package com.github.arthurfiorette.sinklibrary.data.storage;

import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.data.repository.Repository;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @param <K> Storage key type
 * @param <V> Storage value type
 * @param <R> Database raw type
 *
 * @author https://github.com/arthurfiorette/sink-library/
 */
public interface Storage<K, V, R> extends Repository<K, V> {
  R serialize(V object);

  V deserialize(R raw);

  CompletableFuture<Collection<V>> operation(
    Function<Database<K, R>, Collection<R>> func
  );

  CompletableFuture<V> operate(Function<Database<K, R>, R> func);

  default Collection<V> operationSync(
    final Function<Database<K, R>, Collection<R>> func
  ) {
    return this.operation(func).join();
  }

  default V operateSync(final Function<Database<K, R>, R> func) {
    return this.operate(func).join();
  }
}
