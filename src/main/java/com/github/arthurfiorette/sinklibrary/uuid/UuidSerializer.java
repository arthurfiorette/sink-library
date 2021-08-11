package com.github.arthurfiorette.sinklibrary.uuid;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.UUID;

public class UuidSerializer implements JsonDeserializer<UUID>, JsonSerializer<UUID> {

  @Override
  public JsonElement serialize(
    final UUID src,
    final Type typeOfSrc,
    final JsonSerializationContext context
  ) {
    return new JsonPrimitive(FastUuid.toString(src));
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
