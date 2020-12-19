package com.github.hazork.sinkspigot.menu.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.github.hazork.sinkspigot.SinkPlugin;
import com.github.hazork.sinkspigot.interfaces.Registrable;
import com.github.hazork.sinkspigot.menu.SinkMenu;
import com.github.hazork.sinkspigot.menu.item.MenuItem;

public class MenuListener implements Listener, Registrable {

    private final SinkPlugin owner;

    public MenuListener(SinkPlugin owner) {
	this.owner = owner;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onInventoryClick(InventoryClickEvent event) {
	Inventory inv = event.getInventory();
	if (inv.getHolder() instanceof SinkMenu) {
	    event.setCancelled(true);
	    InventoryAction action = event.getAction();
	    int slot = event.getRawSlot();
	    if (slot < inv.getSize() && action != InventoryAction.NOTHING) {
		SinkMenu menu = (SinkMenu) inv.getHolder();
		MenuItem item = menu.getItems().get(slot);

		if (item != null) {
		    item.getClickAction().click(action);
		}
	    }
	}
    }

    @Override
    public void register() {
	Bukkit.getPluginManager().registerEvents(this, getPlugin());
    }

    public SinkPlugin getPlugin() {
	return owner;
    }

}
