package com.github.arthurfiorette.sinklibrary.data.storage.uuid;

import java.util.UUID;

import com.github.arthurfiorette.sinklibrary.data.database.Database;
import com.github.arthurfiorette.sinklibrary.data.storage.gson.GsonLoadingStorage;
import com.github.arthurfiorette.sinklibrary.interfaces.Identifiable;
import com.github.arthurfiorette.sinklibrary.plugin.BasePlugin;
import com.google.gson.JsonObject;

public class UuidLoadingStorage<V extends Identifiable> extends GsonLoadingStorage<UUID, V> {

  public UuidLoadingStorage(Database<UUID, JsonObject> database, Class<V> clazz, BasePlugin plugin) {
    super(database, clazz, plugin);
  }

}
