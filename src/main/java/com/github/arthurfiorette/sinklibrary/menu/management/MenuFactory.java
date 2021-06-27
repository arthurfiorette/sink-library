package com.github.arthurfiorette.sinklibrary.menu.management;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import org.bukkit.entity.Player;

/**
 * Simple interface to target all classes that can create an SinkMenu.
 *
 * @author https://github.com/arthurfiorette/sink-library/
 */
public interface MenuFactory {
  BaseMenu create(BasePlugin plugin, Player player);
}
