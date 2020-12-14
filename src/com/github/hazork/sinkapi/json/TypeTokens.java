package com.github.hazork.sinkapi.json;

import java.lang.reflect.Type;

import com.google.common.reflect.TypeToken;

@SuppressWarnings("serial")
public class TypeTokens {

    public static <T> Type getType() {
	return new TypeToken<T>() {}.getType();
    }

    public static <T> Type getType(T object) {
	return new TypeToken<T>() {}.getType();
    }

}
