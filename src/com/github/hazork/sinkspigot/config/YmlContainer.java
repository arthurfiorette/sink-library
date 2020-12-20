package com.github.hazork.sinkspigot.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.github.hazork.sinkspigot.SinkHelper;
import com.github.hazork.sinkspigot.SinkPlugin;

public class YmlContainer implements SinkHelper {

    private final SinkPlugin plugin;

    private Map<String, YmlFile> fileMap = new HashMap<>();

    public YmlContainer(SinkPlugin plugin) {
	this.plugin = plugin;
    }

    @Override
    public SinkPlugin getPlugin() {
	return plugin;
    }

    public void reloadAll() {
	fileMap.values().stream().forEach(YmlFile::load);
    }

    public void addFile(YmlFile file) {
	this.fileMap.put(file.getName(), file);
    }

    public YmlFile getFile(String name) {
	return this.fileMap.get(name);
    }

    public Collection<YmlFile> getAll() {
	return this.fileMap.values();
    }

}
