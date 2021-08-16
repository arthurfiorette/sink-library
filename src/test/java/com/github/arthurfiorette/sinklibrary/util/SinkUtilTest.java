package com.github.arthurfiorette.sinklibrary.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class SinkUtilTest {

  @Test
  public void arrayGeneric() {
    final String[] input = { "a", "b", "c", "d" };

    // Tests with same values
    final String[] generated = SinkUtil.array(input);

    assertArrayEquals(input, generated);
  }

  @Test
  public void mapperList() {
    final List<String> input = Lists.newArrayList("a", "b", "c", "d");

    final List<Object> generated = SinkUtil.mapper(input, String::length);

    final boolean sameSize = generated.size() == input.size();
    final boolean onlyInteger = generated.stream().allMatch(Integer.class::isInstance);

    assertTrue(onlyInteger);
    assertTrue(sameSize);
  }

  @Test
  public void mapperSet() {
    final Set<String> input = Sets.newHashSet("a", "b", "c", "d");

    final Set<Integer> generated = SinkUtil.mapper(input, String::length);

    // We cant assert for the same size because if the list has same length
    // elements it'll be different.

    final boolean onlyInteger = generated.stream().allMatch(Integer.class::isInstance);
    assertTrue(onlyInteger);
  }

  @Test
  public void randomElement() {
    final List<String> input = Lists.newArrayList("a", "b", "c", "d");

    final String random = SinkUtil.randomElement(input);

    assertTrue(input.contains(random));
  }

  @Test
  public void removeFirst() {
    final String[] input = { "a", "b", "c", "d" };
    final String[] inputShift = { "b", "c", "d" };

    // Tests with same values
    final String[] generated = SinkUtil.removeFirst(input);

    final boolean sameLength = generated.length - 1 == input.length;

    assertArrayEquals(generated, inputShift);
    assertTrue(sameLength);
  }

}
