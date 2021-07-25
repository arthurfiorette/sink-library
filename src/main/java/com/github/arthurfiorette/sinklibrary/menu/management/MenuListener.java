package com.github.arthurfiorette.sinklibrary.menu.management;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.github.arthurfiorette.sinklibrary.core.BaseModule;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.listener.SinkListener;
import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import com.github.arthurfiorette.sinklibrary.menu.item.MenuItem;
import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;

public final class MenuListener extends SinkListener {

  public MenuListener(final BasePlugin plugin) {
    super(plugin);
  }

  @Override
  @EventHandler(ignoreCancelled = false, priority = EventPriority.LOWEST)
  public void onInventoryClick(final InventoryClickEvent event) {
    final Inventory inv = event.getInventory();

    if (inv == null || !(inv.getHolder() instanceof BaseMenu)) {
      return;
    }

    final BaseMenu menu = (BaseMenu) inv.getHolder();
    final BaseModule menuPlugin = menu.getBasePlugin();

    if (!menuPlugin.getClass().equals(this.basePlugin.getClass())) {
      return;
    }

    // Cancel it
    event.setCancelled(true);
    final int slot = event.getRawSlot();

    // Buggy inv
    if (slot > inv.getSize()) {
      return;
    }

    final InventoryAction action = event.getAction();

    // Unknown action
    if (action == InventoryAction.NOTHING || action == InventoryAction.UNKNOWN) {
      return;
    }

    final MenuItem item = menu.getItemAt((byte) slot);

    if (item == null) {
      return;
    }

    final ClickListener listener = item.getListener();

    if (listener == null) {
      return;
    }

    listener.onClick(event.getCurrentItem(), action);
  }
}
