package com.github.hazork.sinkspigot.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.github.hazork.sinkspigot.menu.item.MenuItem;

public abstract class SinkMenu implements InventoryHolder {

    private final Player player;
    private final String title;
    private final int rows;

    protected Map<Integer, MenuItem> itemMap = new HashMap<>();
    private Inventory inventory;

    public SinkMenu(Player player, String title, int rows) {
	this.player = player;
	this.title = title;
	this.rows = rows;
    }

    protected abstract List<MenuItem> items();

    public void draw() {
	itemMap = items().stream().collect(Collectors.toMap(MenuItem::getSlot, m -> m));
	itemMap.forEach((slot, item) -> getInventory().setItem(slot, item.getItemStack()));
    }

    public void show(boolean redraw) {
	if (inventory == null) {
	    inventory = Bukkit.createInventory(this, rows * 9, title);
	    redraw = true;
	}
	if (redraw) {
	    draw();
	}
	player.openInventory(getInventory());
    }

    public Player getPlayer() {
	return player;
    }

    public String getTitle() {
	return title;
    }

    public int getRows() {
	return rows;
    }

    @Override
    public Inventory getInventory() {
	return inventory;
    }

    public Map<Integer, MenuItem> getItems() {
	return itemMap;
    }
}
