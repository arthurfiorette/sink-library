package com.github.hazork.sinkspigot.services.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.ObjectArrays;

/**
 * A service class that handles handles anything in java.
 *
 * @author https://github.com/Hazork/sink-library/
 */
public final class JavaServices {

    /**
     * A private constructor prevent callers from accidentally instantiating an
     * instance
     */
    private JavaServices() {}

    /**
     * Returns an array with the collection elements.
     *
     * @param <T> the array type
     * @param clazz the clazz to identify the array type
     * @param coll the collection to fill in the array
     * 
     * @return the array with the elements
     */
    public static <T> T[] toArray(Class<T> clazz, Collection<T> coll) {
	T[] arr = ObjectArrays.newArray(clazz, coll.size());
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
    public static <T> T[] toArray(Class<T> clazz, Object... values) {
	return toArray(clazz, toList(clazz, values));
    }

    /**
     * Convery any vargarg to a list of the same type
     *
     * @param <T> the list type
     * @param clazz the class to identify the list type
     * @param values the varargs values to fill in the list.
     * 
     * @return the list with the elements
     */
    public static <T> List<T> toList(Class<T> clazz, Object... values) {
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
    public static <T> T[] removeFirst(T[] arr) {
	return Arrays.copyOfRange(arr, 1, arr.length);
    }

    /**
     * Checks if all the varargs aren't nulls.
     *
     * @param objects any objects that can be null
     * 
     * @return true if all the objects aren't null
     */
    public static boolean nonNull(Object... objects) {
	return Arrays.stream(objects).allMatch(Objects::nonNull);
    }

    /**
     * Checks if all the varargs aren't nulls, and if there's at least 1 null
     * throws a NPE.
     *
     * @param objects any objects that can be null
     * 
     * @throws NullPointerException if this varargs have at least 1 null
     */
    public static void requireNonNull(Object... objects) {
	if (!nonNull(objects)) {
	    throw new NullPointerException();
	}
    }

    /**
     * Checks if all the varargs aren't nulls, and if there's at least 1 null
     * throws a NPE with specified message.
     *
     * @param message the message shown if an NPE is thrown
     * @param objects any objects that can be null
     * 
     * @throws NullPointerException if this varargs have at least 1 null
     */
    public static void requireNonNull(String message, Object... objects) {
	if (!nonNull(objects)) {
	    throw new NullPointerException(message);
	}
    }

    public static <T, R> Set<R> setMapper(Set<T> set, Function<T, R> mapper) {
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
    public static <T, R> List<R> listMapper(List<T> list, Function<T, R> mapper) {
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
    public static <T> T getRandomElement(Collection<T> coll) {
	return new ArrayList<>(coll).get(getRandomInt(coll.size()));
    }

    /**
     * Return a random integer with given range
     *
     * @param range the range, 0 to range.
     * 
     * @return the random generated int
     */
    public static int getRandomInt(int range) {
	return new Random().nextInt(range);
    }

}
