package com.github.hazork.sinkspigot.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import com.google.common.collect.Maps;

public final class YMLFiles {

    public static Map<String, Map<String, String>> getKeys(FileConfiguration fc, String section) {
	Map<String, Map<String, String>> request = new HashMap<>();
	ConfigurationSection cSection = fc.getConfigurationSection(section);
	cSection.getKeys(false).stream().forEach(key -> request.put(key, Maps.newHashMap()));
	cSection.getKeys(true).stream()
		.forEach(key -> request.get(getFirstKey(key)).put(getLastKeys(key), fc.getString(section + "." + key)));
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
