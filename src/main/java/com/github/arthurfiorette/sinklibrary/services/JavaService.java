package com.github.arthurfiorette.sinklibrary.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.ObjectArrays;

import lombok.experimental.UtilityClass;

/**
 * A service class that handles handles anything in java.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
@UtilityClass
public final class JavaService {

  /**
   * Returns an array with the collection elements.
   *
   * @param <T> the array type
   * @param clazz the clazz to identify the array type
   * @param coll the collection to fill in the array
   *
   * @return the array with the elements
   */
  public static <T> T[] toArray(final Class<T> clazz, final Collection<T> coll) {
    final T[] arr = ObjectArrays.newArray(clazz, coll.size());
    return coll.toArray(arr);
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
  public static <T> T[] toArray(final Class<T> clazz, final Object... values) {
    return JavaService.toArray(clazz, JavaService.toList(clazz, values));
  }

  /**
   * Cast any vararg to a list of the same type
   *
   * @param <T> the list type
   * @param clazz the class to identify the list type
   * @param values the varargs values to fill in the list.
   *
   * @return the list with the elements
   */
  public static <T> List<T> toList(final Class<T> clazz, final Object... values) {
    return Arrays.stream(values).map(clazz::cast).collect(Collectors.toList());
  }

  /**
   * Return a array with the same elements except the first.
   *
   * @param <T> the array type
   * @param arr the filled array
   *
   * @return the array without the first element
   */
  public static <T> T[] removeFirst(final T[] arr) {
    return Arrays.copyOfRange(arr, 1, arr.length);
  }

  public static <T, R> Set<R> setMapper(final Set<T> set, final Function<T, R> mapper) {
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
  public static <T, R> List<R> listMapper(final List<T> list, final Function<T, R> mapper) {
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
  public static <T> T getRandomElement(final Collection<T> coll) {
    return new ArrayList<>(coll).get(JavaService.getRandomInt(coll.size()));
  }

  /**
   * Return a random integer with given range
   *
   * @param range the range, 0 to range.
   *
   * @return the random generated int
   */
  public static int getRandomInt(final int range) {
    return new Random().nextInt(range);
  }
}
