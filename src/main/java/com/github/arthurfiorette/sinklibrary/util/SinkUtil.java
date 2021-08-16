package com.github.arthurfiorette.sinklibrary.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * A service class that handles handles anything in java.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
@UtilityClass
public final class SinkUtil {

  /**
   * Returns an array with the varargs elements
   *
   * @param <T> the array type
   * @param clazz the class to identify the array type
   * @param values the varargs values to fill in the array.
   *
   * @return the array with the elements
   */
  @SuppressWarnings("unchecked")
  public static <T> T[] array(@NonNull final T... values) {
    return values;
  }

  /**
   * Return a array with the same elements except the first.
   *
   * @param <T> the array type
   * @param arr the filled array
   *
   * @return the array without the first element
   */
  @SuppressWarnings("unchecked")
  public static <T> T[] removeFirst(@NonNull final T... values) {
    return Arrays.copyOfRange(values, 1, values.length);
  }

  public static <T, R> Set<R> mapper(@NonNull final Set<T> set,
      @NonNull final Function<T, R> mapper) {
    return set.stream().map(mapper).collect(Collectors.toSet());
  }

  /**
   * Converts any list in other list with different type. Like a "cast".
   *
   * @param <T> the initial list type
   * @param <R> the returned list type
   * @param list the list to be mapped
   * @param mapper the mapper function
   *
   * @return the mapped list
   */
  public static <T, R> List<R> mapper(@NonNull final List<T> list,
      @NonNull final Function<T, R> mapper) {
    return list.stream().map(mapper).collect(Collectors.toList());
  }

  /**
   * Returns a random element from any collection.
   *
   * @param <T> the collection type
   * @param coll the collection with the elements
   *
   * @return the random element
   */
  public static <T> T randomElement(@NonNull final Collection<T> coll) {
    final int random = ThreadLocalRandom.current().nextInt(coll.size());
    return new ArrayList<>(coll).get(random);
  }

  /**
   * Returns a random element from any collection.
   *
   * @param <T> the collection type
   * @param coll the collection with the elements
   *
   * @return the random element
   */
  @SuppressWarnings("unchecked")
  public static <T> boolean arrayContains(final T element, @NonNull final T... array) {
    if (element == null) {
      for(final T t: array) {
        if (t == null) {
          return true;
        }
      }
    } else {
      for(final T t: array) {
        if (element.equals(t)) {
          return true;
        }
      }
    }

    return false;
  }

}
