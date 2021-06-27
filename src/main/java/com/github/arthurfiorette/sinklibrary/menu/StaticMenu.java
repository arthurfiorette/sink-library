package com.github.arthurfiorette.sinklibrary.menu;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.menu.item.MenuItem;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class StaticMenu implements BaseMenu {

  protected final BasePlugin plugin;
  protected final String title;
  protected final int rows;

  protected Map<Byte, MenuItem> lastStaticItems;
  protected Inventory inventory;

  protected abstract Map<Byte, MenuItem> staticItems();

  public StaticMenu(BasePlugin plugin, String title, int rows) {
    this.plugin = plugin;
    this.inventory = Bukkit.createInventory(this, rows * 9, title);
    this.title = inventory.getTitle();
    this.rows = inventory.getSize() % 9;
    update(); // Calls the first draw
  }

  public StaticMenu(BasePlugin plugin, Inventory inventory) {
    if (this != inventory.getHolder()) {
      throw new IllegalArgumentException("The inventory holder must be this instance");
    }
    this.plugin = plugin;
    this.inventory = inventory;
    this.title = inventory.getTitle();
    this.rows = inventory.getSize() % 9;
    update(); // Calls the first draw
  }

  public void openFor(Player player, boolean update) {
    if (update) this.update();
    player.openInventory(getInventory());
  }

  @Override
  public void update() {
    lastStaticItems = staticItems();
    lastStaticItems.forEach(
      (slot, item) -> {
        inventory.setItem(slot, item.getItem());
      }
    );
  }

  @Override
  public MenuItem getItemAt(byte slot) {
    return lastStaticItems.get(slot);
  }

  @Override
  public BasePlugin getPlugin() {
    return plugin;
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
