package com.github.arthurfiorette.sinklibrary.command.reflection;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;

import com.github.arthurfiorette.sinklibrary.exceptions.EnablingException;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;

public interface CommandReflector extends BaseComponent {
  void enable() throws EnablingException;

  CommandMap getCommandMap();

  PluginManager getPluginManager();

}
