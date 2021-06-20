package com.github.arthurfiorette.sinklibrary.config.files;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.config.YmlFile;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;

/**
 * The config file is a specific implementation of yml file that is exactly
 * build for handling the config.yml of an plugin.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public class ConfigFile implements YmlFile, BaseComponent {

  private static final String NAME = "config.yml";

  private final BasePlugin plugin;

  /**
   * Constructs a config file.
   *
   * @param plugin the plugin owner;
   */
  public ConfigFile(BasePlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void load() {
    this.plugin.reloadConfig();
  }

  @Override
  public void setup(String defaultPath, boolean replace) {
    this.plugin.saveResource(defaultPath, replace);
  }

  @Override
  public FileConfiguration getConfig() {
    return this.plugin.getConfig();
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public File asFile() {
    throw new UnsupportedOperationException(
        "For safety reasons, you do not want to modify the file used as config.yml");
  }

  @Override
  public BasePlugin getPlugin() {
    return this.plugin;
  }
}
