package com.github.hazork.sinkspigot.json;

import java.lang.reflect.Type;

import com.google.common.reflect.TypeToken;

/**
 * A class based on Type to auto-detect the object type to serialize/deserialize
 * in json.
 *
 * @author https://github.com/Hazork/sink-library/
 * @see {@link java.lang.reflect.Type}
 * @see {@link com.github.hazork.sinkspigot.json.Gsons}
 */
@SuppressWarnings("serial")
public final class TypeTokens {

    /**
     * A private constructor prevent callers from accidentally instantiating an
     * instance.
     */
    public TypeTokens() {}

    public static <T> Type getType() {
	return new TypeToken<T>() {}.getType();
    }

    public static <T> Type getType(T object) {
	return new TypeToken<T>() {}.getType();
    }

}
