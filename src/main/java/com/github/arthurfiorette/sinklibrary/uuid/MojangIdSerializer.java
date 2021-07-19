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

public class MojangIdSerializer implements JsonDeserializer<UUID>, JsonSerializer<UUID> {

  @Override
  public JsonElement serialize(UUID src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(FastUuid.toMojangId(src));
  }

  @Override
  public UUID deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException {
    String uuid = json.getAsString();
    return uuid.contains("-") ? FastUuid.parseUUID(uuid) : FastUuid.parseMojangId(uuid);
  }
}
