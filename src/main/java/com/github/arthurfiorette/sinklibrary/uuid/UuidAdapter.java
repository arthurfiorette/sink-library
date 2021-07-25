package com.github.arthurfiorette.sinklibrary.uuid;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.UUID;

/**
 * A simple {@link UUID} {@link TypeAdapter} that uses {@link FastUuid} to write
 * and read faster. You can also use {@link MojangIdAdapter} to use the mojang
 * id format
 */
public final class UuidAdapter extends TypeAdapter<UUID> {

  @Override
  public void write(final JsonWriter out, final UUID value) throws IOException {
    out.value(FastUuid.toString(value));
  }

  @Override
  public UUID read(final JsonReader in) throws IOException {
    final String uuid = in.nextString();
    return uuid.contains("-") ? FastUuid.parseUUID(uuid) : FastUuid.parseMojangId(uuid);
  }
}
