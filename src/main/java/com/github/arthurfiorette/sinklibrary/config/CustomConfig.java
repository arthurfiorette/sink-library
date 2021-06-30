package com.github.arthurfiorette.sinklibrary.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.github.arthurfiorette.sinklibrary.BasePlugin;

public class CustomConfig implements BaseConfig {

  protected final File file;
  protected final BasePlugin plugin;

  protected FileConfiguration config;

  /**
   * @see {@link org.bukkit.plugin.Plugin#saveResource(String, boolean)}
   */
  public CustomConfig(BasePlugin plugin, String resourcePath, boolean replaceIfExists) {
    this.plugin = plugin;
    this.file = new File(plugin.getDataFolder(), resourcePath);
    plugin.saveResource(resourcePath, replaceIfExists);
    this.reload();
  }

  @Override
  public void reload() {
    this.config = YamlConfiguration.loadConfiguration(file);
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
  public BasePlugin getPlugin() {
    return this.plugin;
  }

}
