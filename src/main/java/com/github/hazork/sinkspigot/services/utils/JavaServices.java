package com.github.hazork.sinkspigot.services.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.ObjectArrays;

public final class JavaServices {

    private JavaServices() {}

    public static <T> T[] toArray(Class<T> clazz, Collection<T> coll) {
	T[] arr = ObjectArrays.newArray(clazz, coll.size());
	return coll.toArray(arr);
    }

    public static <T> T[] toArray(Class<T> clazz, Object... values) {
	return toArray(clazz, toList(clazz, values));
    }

    public static <T> List<T> toList(Class<T> clazz, Object... values) {
	return Arrays.stream(values).map(clazz::cast).collect(Collectors.toList());
    }

    public static <T> List<T> removeFirstList(T[] arr) {
	return Arrays.asList(removeFirstElement(arr));
    }

    public static <T> T[] removeFirstElement(T[] arr) {
	return Arrays.copyOfRange(arr, 1, arr.length);
    }

    public static boolean nonNull(Object... objects) {
	return Arrays.stream(objects).allMatch(Objects::nonNull);
    }

    public static void requireNonNull(Object... objects) {
	if (!nonNull(objects)) {
	    throw new NullPointerException();
	}
    }

    public static void requireNonNull(String message, Object... objects) {
	if (!nonNull(objects)) {
	    throw new NullPointerException(message);
	}
    }

    public static <R> List<R> listMapper(List<String> list, Function<String, R> mapper) {
	return list.stream().map(mapper).collect(Collectors.toList());
    }

    public static <R> List<R> listMapperFilter(List<String> list, Function<String, R> mapper, Predicate<R> predicate) {
	return list.stream().map(mapper).filter(predicate).collect(Collectors.toList());
    }

    public static <T> T randomElement(Collection<T> coll) {
	return new ArrayList<>(coll).get(randomInt(coll.size()));
    }

    public static int randomInt(int range) {
	return new Random().nextInt();
    }

}
