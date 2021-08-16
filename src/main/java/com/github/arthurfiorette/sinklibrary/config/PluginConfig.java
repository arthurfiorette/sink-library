package com.github.arthurfiorette.sinklibrary.config;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import java.io.File;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

public class PluginConfig implements BaseConfig {

  public static final String FILENAME = "config.yml";

  @Getter
  protected final File file;

  @Getter
  protected final BasePlugin basePlugin;

  public PluginConfig(final BasePlugin plugin) {
    basePlugin = plugin;
    file = new File(plugin.getDataFolder(), PluginConfig.FILENAME);
    plugin.saveDefaultConfig();
    reload();
  }

  @Override
  public void reload() {
    basePlugin.reloadConfig();
  }

  @Override
  public FileConfiguration getConfig() {
    return basePlugin.getConfig();
  }
}
