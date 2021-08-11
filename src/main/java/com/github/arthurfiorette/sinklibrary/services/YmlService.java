package com.github.arthurfiorette.sinklibrary.services;

import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * A service class that handles with yml.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
@UtilityClass
public class YmlService {

  /**
   * Returns a map with the key being the name of the subsections and the value
   * is a map of the key names and with the loaded values.
   *
   * @param file configuration file where values will be fetched
   * @param section the section name to search for keys and values
   *
   * @return the value map
   */
  public Map<String, Map<String, String>> getKeys(
    final FileConfiguration file,
    final String section
  ) {
    final Map<String, Map<String, String>> request = new HashMap<>();
    final ConfigurationSection cSection = file.getConfigurationSection(section);
    cSection.getKeys(false).stream().forEach(key -> request.put(key, Maps.newHashMap()));
    cSection
      .getKeys(true)
      .stream()
      .forEach(
        key ->
          request
            .get(YmlService.getFirstKey(key))
            .put(YmlService.getLastKeys(key), file.getString(section + "." + key))
      );
    return request;
  }

  private String[] getKeys(final String path) {
    return path.split("\\.");
  }

  private String getFirstKey(final String path) {
    return YmlService.getKeys(path)[0];
  }

  private String getLastKeys(final String path) {
    final String[] keys = YmlService.getKeys(path);
    return String.join(".", Arrays.copyOfRange(keys, 1, keys.length));
  }
}
