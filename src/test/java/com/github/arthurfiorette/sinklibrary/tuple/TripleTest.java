package com.github.arthurfiorette.sinklibrary.tuple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;
import org.junit.jupiter.api.Test;

public class TripleTest {

  private final UUID id1 = UUID.randomUUID();
  private final UUID id2 = UUID.randomUUID();
  private final UUID id3 = UUID.randomUUID();

  @Test
  public void testEmpty() {
    final Triple<UUID, UUID, UUID> pair = new Triple<>();

    assertNull(pair.getLeft());
    assertNull(pair.getMiddle());
    assertNull(pair.getRight());
  }

  @Test
  public void testFull() {
    final Triple<UUID, UUID, UUID> pair = new Triple<>(id1, id2, id3);

    assertEquals(id1, pair.getLeft());
    assertEquals(id2, pair.getMiddle());
    assertEquals(id3, pair.getRight());
  }

  @Test
  public void testStatic() {
    final Triple<UUID, UUID, UUID> pair = new Triple<>(id1, id2, id3);

    assertEquals(id1, pair.getLeft());
    assertEquals(id2, pair.getMiddle());
    assertEquals(id3, pair.getRight());
  }
}
