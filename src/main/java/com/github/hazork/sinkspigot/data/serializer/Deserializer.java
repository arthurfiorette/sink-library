package com.github.hazork.sinkspigot.data.serializer;

/**
 * Simple interface to mark any object R that can deserialized into O
 * 
 * @param <O> the object type
 * @param <R> the serialized type
 * 
 * @author https://github.com/Hazork/sink-library/
 */
public interface Deserializer<O, R> {

    /**
     * Deserialize any object
     * 
     * @param raw the serialized object
     * 
     * @return the deserialized object
     */
    O deserialize(R raw);

}
