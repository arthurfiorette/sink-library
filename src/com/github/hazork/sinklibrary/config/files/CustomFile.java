package com.github.hazork.sinklibrary.config.files;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.github.hazork.sinklibrary.SinkPlugin;
import com.github.hazork.sinklibrary.config.YMLFile;

public class CustomFile implements YMLFile {

    private final SinkPlugin plugin;
    private final File file;
    private final String name;
    private FileConfiguration config;

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
}
