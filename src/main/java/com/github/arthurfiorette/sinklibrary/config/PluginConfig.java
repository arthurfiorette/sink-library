package com.github.arthurfiorette.sinklibrary.config;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;

public class PluginConfig implements BaseConfig {

  public static final String FILENAME = "config.yml";

  protected final File file;
  protected final BasePlugin plugin;

  public PluginConfig(final BasePlugin plugin) {
    this.plugin = plugin;
    this.file = new File(plugin.getDataFolder(), PluginConfig.FILENAME);
  }

  @Override
  public void reload() {
    this.plugin.reloadConfig();
  }

  @Override
  public FileConfiguration getConfig() {
    return this.plugin.getConfig();
  }

  @Override
  public File getFile() {
    return this.file;
  }

  @Override
  public BasePlugin getBasePlugin() {
    return this.plugin;
  }
}
