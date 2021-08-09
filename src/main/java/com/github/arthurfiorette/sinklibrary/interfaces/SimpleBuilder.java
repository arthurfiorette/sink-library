package com.github.arthurfiorette.sinklibrary.interfaces;

/**
 * Represents a simple builder class with two methods.
 *
 * @param <T> the item type
 */
public interface SimpleBuilder<T> {
  T build();

  SimpleBuilder<T> copy();
}
