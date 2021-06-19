package com.github.arthurfiorette.sinklibrary.config.files;

import com.github.arthurfiorette.sinklibrary.SinkHelper;
import com.github.arthurfiorette.sinklibrary.SinkPlugin;
import com.github.arthurfiorette.sinklibrary.config.YmlFile;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * A custom file is a default implementation for any yml file, it's a simple way
 * to get custom files or when there's a file for every player and etc... This
 * can be also implemented for more usages
 *
 * @author https://github.com/Hazork/sink-library/
 */
public class CustomFile implements YmlFile, SinkHelper {

  private final SinkPlugin plugin;
  private final File file;
  private final String name;
  private FileConfiguration config;

  /**
   * Constructs a custom file
   *
   * @param plugin the plugin owner;
   * @param folder the folder location
   * @param name the file name
   */
  public CustomFile(SinkPlugin plugin, File folder, String name) {
    this.plugin = plugin;
    this.name = name.endsWith(".yml") ? name : name + ".yml";
    file = new File(folder, this.name);
  }

  @Override
  public void load() {
    config = YamlConfiguration.loadConfiguration(file);
  }

  @Override
  public void setup(String defaultPath, boolean replace) {
    plugin.saveResource(defaultPath, replace);
  }

  @Override
  public FileConfiguration getConfig() {
    return config;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public File asFile() {
    return file;
  }

  @Override
  public SinkPlugin getPlugin() {
    return plugin;
  }
}
