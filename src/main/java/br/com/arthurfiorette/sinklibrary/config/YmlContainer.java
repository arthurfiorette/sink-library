package br.com.arthurfiorette.sinklibrary.config;

import br.com.arthurfiorette.sinklibrary.SinkHelper;
import br.com.arthurfiorette.sinklibrary.SinkPlugin;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a container for handle all the yml files from a single plugin
 *
 * @author https://github.com/Hazork/sink-library/
 */
public class YmlContainer implements SinkHelper {

  private final SinkPlugin plugin;
  private Map<String, YmlFile> fileMap = new HashMap<>();

  /**
   * Constructs a YmlContainer for a plugin.
   *
   * @param plugin the plugin owner;
   */
  public YmlContainer(SinkPlugin plugin) {
    this.plugin = plugin;
  }

  /**
   * Reloads all the yml files associated in this container
   */
  public void reloadAll() {
    fileMap.values().stream().forEach(YmlFile::load);
  }

  /**
   * Associated an yml file with this container
   *
   * @param file the yml file
   */
  public void addFile(YmlFile file) {
    fileMap.put(file.getName(), file);
  }

  /**
   * Returns the yml file associated with this name
   *
   * @param name the file name
   *
   * @return the yml file or null if not found.
   */
  public YmlFile getFile(String name) {
    return fileMap.get(name);
  }

  /**
   * @return all yml files in this container
   */
  public Collection<YmlFile> getAll() {
    return fileMap.values();
  }

  @Override
  public SinkPlugin getPlugin() {
    return plugin;
  }
}
