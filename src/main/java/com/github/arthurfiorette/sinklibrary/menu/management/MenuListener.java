package com.github.arthurfiorette.sinklibrary.menu.management;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.listener.SinkListener;
import com.github.arthurfiorette.sinklibrary.menu.SinkMenu;
import com.github.arthurfiorette.sinklibrary.menu.item.MenuItem;

/**
 * This class is instantiated to handle all listeners for all menus in a
 * specified plugin.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public final class MenuListener extends SinkListener {

  public MenuListener(BasePlugin owner) {
    super(owner);
  }

  @Override
  @EventHandler(priority = EventPriority.LOW)
  public void onInventoryClick(InventoryClickEvent event) {
    Inventory inv = event.getInventory();
    if (inv.getHolder() instanceof SinkMenu) {

      SinkMenu menu = (SinkMenu) inv.getHolder();
      if (menu.getPlugin().equals(this.getPlugin())) {

        event.setCancelled(true);
        InventoryAction action = event.getAction();
        int slot = event.getRawSlot();
        if (slot < inv.getSize() && action != InventoryAction.NOTHING) {

          MenuItem item = menu.getItems().get(slot);
          if (item != null) {

            item.getClickAction().click(event.getCurrentItem(), action);
          }
        }
      }
    }
  }
}
