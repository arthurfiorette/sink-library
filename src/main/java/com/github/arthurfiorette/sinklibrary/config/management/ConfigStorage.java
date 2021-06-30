package com.github.arthurfiorette.sinklibrary.config.management;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.config.BaseConfig;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import java.util.EnumMap;

public class ConfigStorage<L extends Enum<L> & ConfigLoader> implements BaseComponent {

  protected final EnumMap<L, BaseConfig> configs;
  protected final Class<L> clazz;
  protected final BasePlugin plugin;

  public ConfigStorage(BasePlugin plugin, Class<L> clazz) {
    this.plugin = plugin;
    this.clazz = clazz;
    this.configs = new EnumMap<>(clazz);
  }

  @SuppressWarnings("unchecked")
  public <T extends BaseConfig> T get(L loader) {
    BaseConfig config = this.configs.get(loader);

    if (config == null) {
      config = loader.load(plugin);
      this.configs.put(loader, config);
    }

    return (T) config;
  }

  public BasePlugin getPlugin() {
    return plugin;
  }
}
