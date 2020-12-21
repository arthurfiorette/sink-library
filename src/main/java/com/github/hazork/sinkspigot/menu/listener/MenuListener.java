package com.github.hazork.sinkspigot.menu.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.github.hazork.sinkspigot.SinkPlugin;
import com.github.hazork.sinkspigot.listener.SinkListener;
import com.github.hazork.sinkspigot.menu.SinkMenu;
import com.github.hazork.sinkspigot.menu.item.MenuItem;

public final class MenuListener extends SinkListener {

    private final SinkPlugin owner;

    private MenuListener(SinkPlugin owner) {
	this.owner = owner;
    }

    @Override
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onInventoryClick(InventoryClickEvent event) {
	Inventory inv = event.getInventory();
	if (inv.getHolder() instanceof SinkMenu) {
	    SinkMenu menu = (SinkMenu) inv.getHolder();
	    if (menu.getPlugin().equals(getPlugin())) {
		event.setCancelled(true);
		InventoryAction action = event.getAction();
		int slot = event.getRawSlot();
		if (slot < inv.getSize() && action != InventoryAction.NOTHING) {
		    MenuItem item = menu.getItems().get(slot);

		    if (item != null) {
			item.getClickAction().click(action);
		    }
		}
	    }
	}
    }

    @Override
    public SinkPlugin getPlugin() {
	return owner;
    }

}
