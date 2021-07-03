package com.github.arthurfiorette.sinklibrary.config.management;

import java.util.EnumMap;

import com.github.arthurfiorette.sinklibrary.config.BaseConfig;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;

public class ConfigStorage<L extends Enum<L> & ConfigLoader> implements BaseComponent {

  protected final EnumMap<L, BaseConfig> configs;
  protected final Class<L> clazz;
  protected final BasePlugin plugin;

  public ConfigStorage(final BasePlugin plugin, final Class<L> clazz) {
    this.plugin = plugin;
    this.clazz = clazz;
    this.configs = new EnumMap<>(clazz);
  }

  @SuppressWarnings("unchecked")
  public <T extends BaseConfig> T get(final L loader) {
    BaseConfig config = this.configs.get(loader);

    if (config == null) {
      config = loader.load(this.plugin);
      this.configs.put(loader, config);
    }

    return (T) config;
  }

  @Override
  public BasePlugin getBasePlugin() {
    return this.plugin;
  }
}
