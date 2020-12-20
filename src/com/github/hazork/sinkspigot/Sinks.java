package com.github.hazork.sinkspigot;

import java.util.Map;

import com.google.common.base.Verify;
import com.google.common.collect.Maps;

public final class Sinks {

    public static final String VERSION = "v1.0.0";

    private static final String registerError = "Attempt to register a plugin id that already exists: %s.\n Use: SinkPlugin#from to get the instance.";
    private static final String fromError = "Attempt to get a non-existent instance";

    private static Map<Class<? extends SinkPlugin>, SinkPlugin> pluginMap = Maps.newHashMap();

    static <S extends SinkPlugin> void newInstance(S plugin) {
	Class<? extends SinkPlugin> clazz = plugin.getClass();
	Verify.verify(pluginMap.containsKey(clazz), registerError, clazz.getSimpleName());
	pluginMap.put(clazz, plugin);
    }

    public static <T extends SinkPlugin> T from(Class<T> clazz) {
	Verify.verify(pluginMap.containsKey(clazz), fromError);
	return clazz.cast(pluginMap.get(clazz));
    }

}
