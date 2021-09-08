package com.github.arthurfiorette.sinklibrary.tuple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;
import org.junit.jupiter.api.Test;

public class PairTest {

  private final UUID id1 = UUID.randomUUID();
  private final UUID id2 = UUID.randomUUID();

  @Test
  public void testEmpty() {
    final Pair<UUID, UUID> pair = new Pair<>();

    assertNull(pair.getLeft());
    assertNull(pair.getRight());
  }

  @Test
  public void testFull() {
    final Pair<UUID, UUID> pair = new Pair<>(id1, id2);

    assertEquals(id1, pair.getLeft());
    assertEquals(id2, pair.getRight());
  }

  @Test
  public void testStatic() {
    final Pair<UUID, UUID> pair = Pair.pair(id1, id2);

    assertEquals(id1, pair.getLeft());
    assertEquals(id2, pair.getRight());
  }
}
