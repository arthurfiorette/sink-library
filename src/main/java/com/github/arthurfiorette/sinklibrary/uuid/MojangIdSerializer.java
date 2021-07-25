package com.github.arthurfiorette.sinklibrary.uuid;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.UUID;

public class MojangIdSerializer implements JsonDeserializer<UUID>, JsonSerializer<UUID> {

  @Override
  public JsonElement serialize(
    final UUID src,
    final Type typeOfSrc,
    final JsonSerializationContext context
  ) {
    return new JsonPrimitive(FastUuid.toMojangId(src));
  }

  @Override
  public UUID deserialize(
    final JsonElement json,
    final Type typeOfT,
    final JsonDeserializationContext context
  ) throws JsonParseException {
    final String uuid = json.getAsString();
    return uuid.contains("-") ? FastUuid.parseUUID(uuid) : FastUuid.parseMojangId(uuid);
  }
}
