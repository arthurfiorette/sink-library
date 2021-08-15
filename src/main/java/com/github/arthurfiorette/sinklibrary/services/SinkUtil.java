package com.github.arthurfiorette.sinklibrary.services;

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
   * Returns an array with the collection elements.
   *
   * @param <T> the array type
   * @param clazz the clazz to identify the array type
   * @param coll the collection to fill in the array
   *
   * @return the array with the elements
   */
  @SuppressWarnings("unchecked")
  public static <T> T[] array(@NonNull final Class<T> clazz, @NonNull final Collection<T> coll) {
    return coll.toArray((T[]) new Object[coll.size()]);
  }

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
  public static <T> T[] removeFirst(@NonNull final T[] arr) {
    return Arrays.copyOfRange(arr, 1, arr.length);
  }

  public static <T, R> Set<R> mapper(@NonNull final Set<T> set, @NonNull final Function<T, R> mapper) {
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
  public static <T, R> List<R> mapper(@NonNull final List<T> list, @NonNull final Function<T, R> mapper) {
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
    final int random = randomInt(0, coll.size());
    return new ArrayList<>(coll).get(random);
  }

  public static long randomLong(final long min, final long max) {
    return ThreadLocalRandom.current().nextLong(min, max + 1);
  }
  
  public static int randomInt(final int min, final int max) {
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }
  
  public static double randomDouble(final double min, final double max) {
    return ThreadLocalRandom.current().nextDouble(min, max + 1);
  }
}
