package com.github.arthurfiorette.sinklibrary.tuple;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ValidPairTest {

  @Test
  public void testConstructor() {
    assertThrows(
      NullPointerException.class,
      () -> {
        new ValidPair<>(null, null);
      }
    );
  }

  @Test
  public void testStatic() {
    assertThrows(
      NullPointerException.class,
      () -> {
        ValidPair.validPair(null, null);
      }
    );
  }
}
