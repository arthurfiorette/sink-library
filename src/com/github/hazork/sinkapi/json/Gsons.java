package com.github.hazork.sinkapi.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class Gsons {

    private static final Gson gson = new Gson();

    public static <T> T fromJson(String json) {
	return fromJson(gson, json);
    }

    public static <T> T fromJson(Gson gson, String json) {
	return gson.fromJson(json, TypeTokens.getType());
    }

    public static <T> T fromJson(JsonElement json) {
	return fromJson(gson, json);
    }

    public static <T> T fromJson(Gson gson, JsonElement json) {
	return gson.fromJson(json, TypeTokens.getType());
    }

    public static <T> String toJson(T source) {
	return gson.toJson(source, TypeTokens.getType());
    }
}
