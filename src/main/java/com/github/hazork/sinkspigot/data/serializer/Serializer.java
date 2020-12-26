package com.github.hazork.sinkspigot.data.serializer;

/**
 * Simple interface to mark any object O that can serialized into R
 * 
 * @param <O> the object type
 * @param <R> the serialized type
 * 
 * @author https://github.com/Hazork/sink-library/
 */
public interface Serializer<O, R> {

    /**
     * Serialize any object.
     * 
     * @param object the object to serialize
     * 
     * @return the serialized object
     */
    R serialize(O object);

}
