package com.github.arthurfiorette.sinklibrary.uuid;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;

public class FastUuidTest {

  private UUID randomUuid= UUID.randomUUID();
  private String randomUuidString= randomUuid.toString();
  private String randomMojangId= randomUuidString.replaceAll("-", "");

  @Test
  public void checkToString() {
    final String string = FastUuid.toString(randomUuid);

    assertEquals(randomUuidString, string);
  }

  @Test
  public void checkToMojandId() {
    final String string = FastUuid.toMojangId(randomUuid);

    assertEquals(randomMojangId, string);
  }

  @Test
  public void checkFromString() {
    final UUID uuid = FastUuid.parseUUID(randomUuidString);

    assertEquals(uuid, randomUuid);
  }

  @Test
  public void checkFromMojangId() {
    final UUID uuid = FastUuid.parseMojangId(randomMojangId);

    assertEquals(uuid, randomUuid);
  }

}
