package com.github.arthurfiorette.sinklibrary.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;

public class CustomConfig implements BaseConfig {

  protected final File file;
  protected final BasePlugin plugin;

  protected FileConfiguration config;

  /**
   * @see {@link org.bukkit.plugin.Plugin#saveResource(String, boolean)}
   */
  public CustomConfig(
    final BasePlugin plugin,
    final String resourcePath,
    final boolean replaceIfExists
  ) {
    this.plugin = plugin;
    this.file = new File(plugin.getDataFolder(), resourcePath);
    plugin.saveResource(resourcePath, replaceIfExists);
    this.reload();
  }

  @Override
  public void reload() {
    this.config = YamlConfiguration.loadConfiguration(this.file);
  }

  @Override
  public FileConfiguration getConfig() {
    return this.config;
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
