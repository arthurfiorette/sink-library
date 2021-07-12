package com.github.arthurfiorette.sinklibrary.config;

import java.io.File;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import com.github.arthurfiorette.sinklibrary.interfaces.BasePlugin;

public class PluginConfig implements BaseConfig {

  public static final String FILENAME = "config.yml";

  @Getter
  protected final File file;

  @Getter
  protected final BasePlugin basePlugin;

  public PluginConfig(final BasePlugin plugin) {
    this.basePlugin = plugin;
    this.file = new File(plugin.getDataFolder(), PluginConfig.FILENAME);
  }

  @Override
  public void reload() {
    this.basePlugin.reloadConfig();
  }

  @Override
  public FileConfiguration getConfig() {
    return this.basePlugin.getConfig();
  }
}
