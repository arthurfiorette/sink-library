package com.github.hazork.sinkspigot.data;

import java.util.Set;

public interface Database<T> {

    void open();

    void close();

    void save(String key, T value);

    T get(String key);

    Set<T> getAll();

}
