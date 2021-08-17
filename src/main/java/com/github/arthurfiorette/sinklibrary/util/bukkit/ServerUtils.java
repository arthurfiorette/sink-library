package com.github.arthurfiorette.sinklibrary.util.bukkit;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

@UtilityClass
public class ServerUtils {

  /**
   * Checks if this runtime has a plugin loaded with this same name. Useful to
   * check if can use the plugin api.
   *
   * @param name the plugin name
   *
   * @return true if this plugin is enabled
   */
  public boolean isPluginEnabled(final String name) {
    return Bukkit.getPluginManager().isPluginEnabled(name);
  }

  /**
   * Checks if this runtime has a plugin loaded with this same name. Useful to
   * check if can use the plugin api.
   *
   * @param name the plugin name
   *
   * @return true if this plugin is enabled
   */
  public boolean isPluginEnabled(final Plugin plugin) {
    return Bukkit.getPluginManager().isPluginEnabled(plugin);
  }

  public void disablePlugin(final Plugin plugin) {
    Bukkit.getPluginManager().disablePlugin(plugin);
  }
}
