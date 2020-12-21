package com.github.hazork.sinkspigot.config.files;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.hazork.sinkspigot.SinkHelper;
import com.github.hazork.sinkspigot.SinkPlugin;
import com.github.hazork.sinkspigot.config.YmlFile;

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
		"For safety reasons, you do not want to modify the file used as config.yml");
    }

    @Override
    public SinkPlugin getPlugin() {
	return plugin;
    }

}
