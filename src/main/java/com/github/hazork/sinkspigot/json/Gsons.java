package com.github.hazork.sinkspigot.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * A class based on Gson to facilitate the json parsing
 *
 * @author https://github.com/Hazork/sink-library/
 * 
 * @see {@link java.lang.reflect.Type}
 * @see {@link com.github.hazork.sinkspigot.json.TypeTokens}
 */
public final class Gsons {

    /**
     * A private constructor prevent callers from accidentally instantiating an
     * instance.
     */
    public Gsons() {}

    /**
     * Transforms a json string into a object.
     *
     * @param <T> the new object type
     * @param json the raw string in json
     * 
     * @return the serialized object
     */
    public static <T> T fromJson(String json) {
	return fromJson(new Gson(), json);
    }

    /**
     * Transforms a json string into a object with a specified Gson.
     *
     * @param <T> the new object type
     * @param gson the specified gson instance to use
     * @param json the raw string in json
     * 
     * @return the deserialized object
     */
    public static <T> T fromJson(Gson gson, String json) {
	return gson.fromJson(json, TypeTokens.getType());
    }

    /**
     * Transforms a json element into a object.
     *
     * @param <T> the new object type
     * @param json the json element to be deserialized
     * 
     * @return the deserialized object
     */
    public static <T> T fromJson(JsonElement json) {
	return fromJson(new Gson(), json);
    }

    /**
     * Transforms a json element into a object with a specified Gson.
     *
     * @param <T> the new object type
     * @param gson the specified gson instance to use
     * @param json the json element to be deserialized
     * 
     * @return the deserialized object
     */
    public static <T> T fromJson(Gson gson, JsonElement json) {
	return gson.fromJson(json, TypeTokens.getType());
    }

    /**
     * Serialize any object into a json string
     *
     * @param <T> the object type
     * @param source the object to be serialized
     * 
     * @return the json string representation of this object
     */
    public static <T> String toJson(T source) {
	return toJson(new Gson(), source);
    }

    /**
     * Serialize any object into a json string with a specified Gson.
     *
     * @param <T> the object type
     * @param gson the specified gson instance to use
     * @param source the object to be serialized
     * 
     * @return the json string representation of this object
     */
    public static <T> String toJson(Gson gson, T source) {
	return gson.toJson(source, TypeTokens.getType());
    }

    /**
     * Serialize any object into a json element.
     *
     * @param <T> the object type
     * @param source the object to be serialized
     * 
     * @return the json element of this object
     */
    public static <T> JsonElement toJsonTree(T source) {
	return toJsonTree(new Gson(), source);
    }

    /**
     * Serialize any object into a json element with a specified Gson.
     *
     * @param <T> the object type
     * @param gson the specified gson instance to use
     * @param source the object to be serialized
     * 
     * @return the json element of this object
     */
    public static <T> JsonElement toJsonTree(Gson gson, T source) {
	return gson.toJsonTree(source, TypeTokens.getType());
    }

    /**
     * Parses any json string to a JsonObject
     * 
     * @param json the json in a string
     * 
     * @return the parsed json object
     */
    public static JsonObject parseJson(String json) {
	return new JsonParser().parse(json).getAsJsonObject();
    }

    /**
     * Parses any object to a JsonObject.
     * 
     * @param <T> the object type
     * @param source the object to be parsed
     * 
     * @return the parsed json object
     */
    public static <T> JsonObject parseJson(T source) {
	String json = (source instanceof String) ? (String) source : toJson(source);
	return parseJson(json);
    }
}
