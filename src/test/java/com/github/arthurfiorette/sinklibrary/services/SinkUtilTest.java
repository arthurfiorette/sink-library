package com.github.arthurfiorette.sinklibrary.services;

import static org.junit.Assert.assertArrayEquals;

import com.github.arthurfiorette.sinklibrary.StringTestUtils;

import java.util.List;

import org.junit.Test;

public class SinkUtilTest {


  @Test
  public void arrayGeneric() {
    final String[] random = StringTestUtils.randomCharArray();

    // Tests with same values
    final String[] generated = SinkUtil.array(random);

    assertArrayEquals(random, generated);
  }

  @Test
  public void arrayCollection() {
    final List<String> random = StringTestUtils.randomCharList();
    final String[] generated = SinkUtil.array(String.class, random);

    assertArrayEquals(random.toArray(new String[0]), generated);
  }

}
