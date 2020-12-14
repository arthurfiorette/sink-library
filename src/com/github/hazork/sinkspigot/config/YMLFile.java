package com.github.hazork.sinkspigot.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

public interface YMLFile {

    void load();

    void setup(String defaultPath, boolean replace);

    FileConfiguration getConfig();

    String getName();

    File asFile();

    default FileConfiguration getConfig(boolean reload) {
	if (reload) {
	    load();
	}
	return getConfig();
    }

}
