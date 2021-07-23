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
   * @param plugin the owner plugin
   * @param resourcePath the embedded resource path to look for within the
   * plugin's .jar file. (No preceding slash).
   * @param replace if true, the embedded resource will overwrite the contents
   * of an existing file.
   *
   * @see org.bukkit.plugin.Plugin#saveResource(String, boolean)
   */
  public CustomConfig(final BasePlugin plugin, final String resourcePath, final boolean replace) {
    this.basePlugin = plugin;
    this.file = new File(plugin.getDataFolder(), resourcePath);
    plugin.saveResource(resourcePath, replace);
    this.reload();
  }

  @Override
  public void reload() {
    this.config = YamlConfiguration.loadConfiguration(this.file);
  }
}
