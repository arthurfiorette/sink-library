package com.github.arthurfiorette.sinklibrary.config;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;

public class PluginConfig implements BaseConfig {

  public static final String FILENAME = "config.yml";

  protected final File file;
  protected final BasePlugin plugin;

  public PluginConfig(BasePlugin plugin) {
    this.plugin = plugin;
    this.file = new File(plugin.getDataFolder(), FILENAME);
  }

  @Override
  public void reload() {
    this.plugin.reloadConfig();
  }

  @Override
  public FileConfiguration getConfig() {
    return plugin.getConfig();
  }

  @Override
  public File getFile() {
    return file;
  }

  @Override
  public BasePlugin getPlugin() {
    return this.plugin;
  }
}
