package com.github.hazork.sinkspigot.data.serializer;

/**
 * Simple interface to mark any object O that can be serialized to R and
 * deserialized back to O.
 * 
 * @param <O> the object type
 * @param <R> the serialized type
 * 
 * @author https://github.com/Hazork/sink-library/
 */
public interface Serializable<O, R> extends Serializer<O, R>, Deserializer<O, R> {

}
