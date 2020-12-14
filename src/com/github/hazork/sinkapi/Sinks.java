package com.github.hazork.sinkapi;

import java.util.Map;

import com.google.common.base.Verify;
import com.google.common.collect.Maps;

public final class Sinks {

    private static Map<Class<? extends SinkPlugin>, SinkPlugin> pluginMap = Maps.newHashMap();

    static <S extends SinkPlugin> void newInstance(S plugin) {
	final String registerError = "Attempt to register a plugin id that already exists: %s.\n Use: SinkPlugin#from to get the instance.";
	Class<? extends SinkPlugin> clazz = plugin.getClass();
	Verify.verify(pluginMap.containsKey(clazz), registerError, clazz.getSimpleName());
	pluginMap.put(clazz, plugin);
    }

    public static <T extends SinkPlugin> T from(Class<T> clazz) {
	Verify.verify(pluginMap.containsKey(clazz), "Attempt to get a non-existent instance");
	return clazz.cast(pluginMap.get(clazz));
    }

}
