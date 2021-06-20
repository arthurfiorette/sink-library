package com.github.arthurfiorette.sinklibrary.menu.management;

import org.bukkit.entity.Player;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.menu.SinkMenu;

/**
 * Simple interface to target all classes that can create an SinkMenu.
 *
 * @author https://github.com/arthurfiorette/sink-library/
 */
public interface MenuFactory {
  SinkMenu create(BasePlugin plugin, Player player);
}
