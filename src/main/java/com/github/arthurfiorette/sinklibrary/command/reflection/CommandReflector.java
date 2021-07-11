package com.github.arthurfiorette.sinklibrary.command.reflection;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.exceptions.EnablingException;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;

public interface CommandReflector extends BaseComponent {
  void enable() throws EnablingException;

  CommandMap getCommandMap();

  PluginManager getPluginManager();

  @Override
  BasePlugin getBasePlugin();
}
