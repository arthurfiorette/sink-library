package com.github.arthurfiorette.sinklibrary.menu.management;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.listener.SinkListener;
import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import com.github.arthurfiorette.sinklibrary.menu.item.MenuItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MenuListener extends SinkListener {

  public MenuListener(final BasePlugin plugin) {
    super(plugin);
  }

  @Override
  @EventHandler(ignoreCancelled = false, priority = EventPriority.LOWEST) // Called
  // first
  public void onInventoryClick(final InventoryClickEvent event) {
    final Inventory inv = event.getInventory();

    // It's an BaseMenu
    if ((inv != null) && (inv.getHolder() instanceof BaseMenu)) {
      final BaseMenu menu = (BaseMenu) inv.getHolder();
      final BasePlugin menuPlugin = menu.getPlugin();

      // It's from the same owner
      if (menuPlugin.getClass().equals(this.plugin.getClass())) {
        // Cancel it
        event.setCancelled(true);
        final InventoryAction action = event.getAction();
        final int slot = event.getRawSlot();

        if ((slot < inv.getSize()) && (action != InventoryAction.NOTHING)) {
          final MenuItem item = menu.getItemAt((byte) slot);

          if (item != null) {
            item.getListener().onClick(event.getCurrentItem(), action);
          }
        }
      }
    }
  }
}
