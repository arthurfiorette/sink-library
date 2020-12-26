package com.github.hazork.sinkspigot.services.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import com.google.common.collect.Maps;

/**
 * A service class that handles with yml.
 *
 * @author https://github.com/Hazork/sink-library/
 */
public final class YmlService {

    /**
     * A private constructor prevent callers from accidentally instantiating an
     * instance.
     */
    public YmlService() {}

    /**
     * Returns a map with the key being the name of the subsections and the
     * value is a map of the key names and with the loaded values.
     *
     * @param file configuration file where values will be fetched
     * @param section the section name to search for keys and values
     * 
     * @return the value map
     */
    public static Map<String, Map<String, String>> getKeys(FileConfiguration file, String section) {
	Map<String, Map<String, String>> request = new HashMap<>();
	ConfigurationSection cSection = file.getConfigurationSection(section);
	cSection.getKeys(false).stream().forEach(key -> request.put(key, Maps.newHashMap()));
	cSection.getKeys(true).stream().forEach(
		key -> request.get(getFirstKey(key)).put(getLastKeys(key), file.getString(section + "." + key)));
	return request;
    }

    private static String[] getKeys(String path) {
	return path.split("\\.");
    }

    private static String getFirstKey(String path) {
	return getKeys(path)[0];
    }

    private static String getLastKeys(String path) {
	String[] keys = getKeys(path);
	return String.join(".", Arrays.copyOfRange(keys, 1, keys.length));
    }

}
