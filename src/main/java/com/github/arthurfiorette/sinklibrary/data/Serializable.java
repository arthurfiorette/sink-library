package com.github.arthurfiorette.sinklibrary.data;

/**
 * Simple interface to mark any object O that can be serialized to R and
 * deserialized back to O.
 *
 * @param <OBJ> the object type
 * @param <RAW> the serialized type
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public interface Serializable<OBJ, RAW> {
  /**
   * Serialize an object.
   *
   * @param object the object to serialize
   *
   * @return the serialized object
   */
  RAW serialize(OBJ object);

  /**
   * Deserialize an object
   *
   * @param raw the serialized object
   *
   * @return the deserialized object
   */
  OBJ deserialize(RAW raw);
}
