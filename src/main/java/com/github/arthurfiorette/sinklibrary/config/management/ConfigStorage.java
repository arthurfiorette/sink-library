package com.github.arthurfiorette.sinklibrary.config.management;

import com.github.arthurfiorette.sinklibrary.component.Component;
import com.github.arthurfiorette.sinklibrary.config.BaseConfig;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;

import java.util.EnumMap;

import lombok.Getter;

public class ConfigStorage<L extends Enum<L> & ConfigLoader> implements Component {

  protected final EnumMap<L, BaseConfig> configs;

  protected final Class<L> clazz;

  @Getter
  protected final BasePlugin basePlugin;

  public ConfigStorage(final BasePlugin plugin, final Class<L> clazz) {
    this.basePlugin = plugin;
    this.clazz = clazz;
    this.configs = new EnumMap<>(clazz);
  }

  @SuppressWarnings("unchecked")
  public <T extends BaseConfig> T get(final L loader) {
    BaseConfig config = this.configs.get(loader);

    if (config == null) {
      config = loader.load(this.basePlugin);
      this.configs.put(loader, config);
    }

    return (T) config;
  }
}
