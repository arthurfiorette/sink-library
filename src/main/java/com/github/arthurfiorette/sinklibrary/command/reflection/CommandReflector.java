package com.github.arthurfiorette.sinklibrary.command.reflection;

import com.github.arthurfiorette.sinklibrary.component.Component;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;

public interface CommandReflector extends Component, Runnable {

  CommandMap getCommandMap();

  PluginManager getPluginManager();
}
