package com.github.hazork.sinkspigot.config;

import com.github.hazork.sinkspigot.SinkPlugin;

public interface YmlHelper {

    SinkPlugin getPlugin();

    default YmlContainer getYmlContainer() {
	return getPlugin().getYmlContainer();
    }

    default YmlFile getYmlFile(String name) {
	return getYmlContainer().getFile(name);
    }

}
