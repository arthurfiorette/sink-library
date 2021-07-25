package com.github.arthurfiorette.sinklibrary.data;

import com.google.common.cache.CacheBuilder;
import java.util.function.Consumer;

@FunctionalInterface
public interface CacheOperator<K, V> extends Consumer<CacheBuilder<K, V>> {
  @SuppressWarnings("unchecked")
  default CacheBuilder<K, V> withNewBuilder() {
    final CacheBuilder<K, V> builder = (CacheBuilder<K, V>) CacheBuilder.newBuilder();
    this.accept(builder);
    return builder;
  }
}
