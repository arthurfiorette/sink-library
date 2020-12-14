package com.github.hazork.sinkapi.config.files;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.hazork.sinkapi.SinkObject;
import com.github.hazork.sinkapi.SinkPlugin;
import com.github.hazork.sinkapi.config.YMLFile;

public class ConfigFile implements YMLFile, SinkObject {

    private static final String NAME = "config.yml";

    private final SinkPlugin plugin;

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
