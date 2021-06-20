package com.github.arthurfiorette.sinklibrary.config;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a container for handle all the yml files from a single plugin
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public class YmlContainer implements BaseComponent {

  private final BasePlugin plugin;
  private final Map<String, YmlFile> fileMap = new HashMap<>();

  /**
   * Constructs a YmlContainer for a plugin.
   *
   * @param plugin the plugin owner;
   */
  public YmlContainer(BasePlugin plugin) {
    this.plugin = plugin;
  }

  /**
   * Reloads all the yml files associated in this container
   */
  public void reloadAll() {
    this.fileMap.values().stream().forEach(YmlFile::load);
  }

  /**
   * Associated an yml file with this container
   *
   * @param file the yml file
   */
  public void addFile(YmlFile file) {
    this.fileMap.put(file.getName(), file);
  }

  /**
   * Returns the yml file associated with this name
   *
   * @param name the file name
   *
   * @return the yml file or null if not found.
   */
  public YmlFile getFile(String name) {
    return this.fileMap.get(name);
  }

  /**
   * @return all yml files in this container
   */
  public Collection<YmlFile> getAll() {
    return this.fileMap.values();
  }

  @Override
  public BasePlugin getPlugin() {
    return this.plugin;
  }
}
