package com.github.hazork.sinkapi.data;

import java.util.Set;

import javax.annotation.Nullable;

public interface Database<T> {

    void open();

    void close();

    void save(String key, T value);

    @Nullable
    T get(String key);

    Set<T> getAll();

}
