package com.github.arthurfiorette.sinklibrary;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringTestUtils {

  public String[] randomCharArray() {
    return RandomStringUtils.random(randomLength()).split("");
  }
  
  public List<String> randomCharList() {
    return Lists.newArrayList(randomCharArray());
  }
  
  public Set<String> randomCharSet() {
    return Sets.newHashSet(randomCharArray());
  }
  
  /**
   * @return a random number to be used in length tests
   */
  public int randomLength() {
    // 0 - 30
   return ThreadLocalRandom.current().nextInt(0, 31);
  }
  
}
