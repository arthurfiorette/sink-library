package com.github.hazork.sinkspigot.config;

import com.github.hazork.sinkspigot.SinkObject;

public interface YmlHelper extends SinkObject {

    default YmlContainer getYmlContainer() {
	return getPlugin().getYmlContainer();
    }

    default YmlFile getYmlFile(String name) {
	return getYmlContainer().getFile(name);
    }

}
