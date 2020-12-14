package com.github.hazork.sinkapi.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import com.google.common.collect.Maps;

public final class YMLFiles {

    /**
     * section<br>
     * section.sec1<br>
     * section.sec1.subsec1<br>
     * section.sec1.subsec1.sub-subsec1<br>
     * section.sec2<br>
     * section.sec2.subsec2<br>
     * <br>
     * <b>getKeys({@linkplain FileConfiguration}, "section")</b> -> <br>
     * Map(section, Map(sec1, {subsec1, subsec1.sub-subsec1...} ))
     */
    public static Map<String, Map<String, String>> getKeys(FileConfiguration fc, String section) {
	Map<String, Map<String, String>> request = new HashMap<>();
	ConfigurationSection cSection = fc.getConfigurationSection(section);
	cSection.getKeys(false).stream().forEach(key -> request.put(key, Maps.newHashMap()));
	cSection.getKeys(true).stream()
		.forEach(key -> request.get(getSectionName(key)).put(getKeys(key), fc.getString(section + "." + key)));
	return request;
    }

    /**
     * section.key1.key2.key3 -> section
     * 
     * @see {@link YMLFiles#getKeys(String)}
     */
    private static String getSectionName(String completeKey) {
	return completeKey.split("\\.")[0];
    }

    /**
     * section.key1.key2.key3 -> key1.key2.key3
     * 
     * @see {@link YMLFiles#getSectionName(String)}
     */
    private static String getKeys(String completeKey) {
	String[] keys = completeKey.split("\\.");
	return String.join(".", Arrays.copyOfRange(keys, 1, keys.length));
    }

}
