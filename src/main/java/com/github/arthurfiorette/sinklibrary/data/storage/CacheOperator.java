package com.github.arthurfiorette.sinklibrary.data.storage;

import java.util.function.Consumer;

import com.google.common.cache.CacheBuilder;

@FunctionalInterface
public interface CacheOperator<K, V> extends Consumer<CacheBuilder<K, V>> {

  @SuppressWarnings("unchecked")
  default CacheBuilder<K, V> withNewBuilder() {
    CacheBuilder<K, V> builder = (CacheBuilder<K, V>) CacheBuilder.newBuilder();
    this.accept(builder);
    return builder;
  }

}
