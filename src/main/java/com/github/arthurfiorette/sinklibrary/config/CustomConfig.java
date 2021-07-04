package com.github.arthurfiorette.sinklibrary.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;

import lombok.Getter;

public class CustomConfig implements BaseConfig {

  @Getter
  protected final File file;

  @Getter
  protected final BasePlugin basePlugin;

  @Getter
  protected FileConfiguration config;

  /**
   * @see {@link org.bukkit.plugin.Plugin#saveResource(String, boolean)}
   */
  public CustomConfig(
    final BasePlugin plugin,
    final String resourcePath,
    final boolean replaceIfExists
  ) {
    this.basePlugin = plugin;
    this.file = new File(plugin.getDataFolder(), resourcePath);
    plugin.saveResource(resourcePath, replaceIfExists);
    this.reload();
  }

  @Override
  public void reload() {
    this.config = YamlConfiguration.loadConfiguration(this.file);
  }
}
