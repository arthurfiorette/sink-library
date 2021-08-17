package com.github.arthurfiorette.sinklibrary.tuple;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class ValidTripleTest {

  @Test
  public void testConstructor() {
    assertThrows(NullPointerException.class, () -> {
      new ValidTriple<>(null, null, null);
    });
  }

  @Test
  public void testStatic() {
    assertThrows(NullPointerException.class, () -> {
      ValidTriple.validTriple(null, null, null);
    });
  }

}
