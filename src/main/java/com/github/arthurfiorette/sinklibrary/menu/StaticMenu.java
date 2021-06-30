package com.github.arthurfiorette.sinklibrary.menu;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.menu.item.MenuItem;

public abstract class StaticMenu implements BaseMenu {

  protected final BasePlugin plugin;
  protected final String title;
  protected final int rows;

  protected Map<Byte, MenuItem> lastStaticItems;
  protected Inventory inventory;

  protected abstract Map<Byte, MenuItem> staticItems();

  public StaticMenu(final BasePlugin plugin, final String title, final int rows) {
    this.plugin = plugin;
    this.inventory = Bukkit.createInventory(this, rows * 9, title);
    this.title = this.inventory.getTitle();
    this.rows = this.inventory.getSize() % 9;
    this.update(); // Calls the first draw
  }

  public StaticMenu(final BasePlugin plugin, final Inventory inventory) {
    if (this != inventory.getHolder()) {
      throw new IllegalArgumentException("The inventory holder must be this instance");
    }
    this.plugin = plugin;
    this.inventory = inventory;
    this.title = inventory.getTitle();
    this.rows = inventory.getSize() % 9;
    this.update(); // Calls the first draw
  }

  public void openFor(final Player player, final boolean update) {
    if (update) {
      this.update();
    }
    player.openInventory(this.getInventory());
  }

  @Override
  public void update() {
    this.lastStaticItems = this.staticItems();
    this.lastStaticItems.forEach(
      (slot, item) -> {
        this.inventory.setItem(slot, item.getItem());
      }
    );
  }

  @Override
  public MenuItem getItemAt(final byte slot) {
    return this.lastStaticItems.get(slot);
  }

  @Override
  public BasePlugin getPlugin() {
    return this.plugin;
  }

  @Override
  public Inventory getInventory() {
    return this.inventory;
  }

  @Override
  public int getRows() {
    return this.rows;
  }
}
