package br.com.arthurfiorette.sinklibrary.data;

/**
 * Simple interface to mark any object O that can be serialized to R and
 * deserialized back to O.
 *
 * @param <O> the object type
 * @param <R> the serialized type
 *
 * @author https://github.com/Hazork/sink-library/
 */
public interface Serializable<O, R> {
  /**
   * Serialize any object.
   *
   * @param object the object to serialize
   *
   * @return the serialized object
   */
  R serialize(O object);

  /**
   * Deserialize any object
   *
   * @param raw the serialized object
   *
   * @return the deserialized object
   */
  O deserialize(R raw);
}
