package br.com.arthurfiorette.sinklibrary.config.files;

import br.com.arthurfiorette.sinklibrary.SinkHelper;
import br.com.arthurfiorette.sinklibrary.SinkPlugin;
import br.com.arthurfiorette.sinklibrary.config.YmlFile;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * The config file is a specific implementation of yml file that is exactly
 * build for handling the config.yml of an plugin.
 *
 * @author https://github.com/Hazork/sink-library/
 */
public class ConfigFile implements YmlFile, SinkHelper {

  private static final String NAME = "config.yml";

  private final SinkPlugin plugin;

  /**
   * Constructs a config file.
   *
   * @param plugin the plugin owner;
   */
  public ConfigFile(SinkPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void load() {
    plugin.reloadConfig();
  }

  @Override
  public void setup(String defaultPath, boolean replace) {
    plugin.saveResource(defaultPath, replace);
  }

  @Override
  public FileConfiguration getConfig() {
    return plugin.getConfig();
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public File asFile() {
    throw new UnsupportedOperationException(
      "For safety reasons, you do not want to modify the file used as config.yml"
    );
  }

  @Override
  public SinkPlugin getPlugin() {
    return plugin;
  }
}
