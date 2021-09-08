package com.github.arthurfiorette.sinklibrary.menu;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.menu.item.MenuItem;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class StaticMenu implements BaseMenu {

  @Getter
  protected final BasePlugin basePlugin;

  @Getter
  protected final int rows;

  @Getter
  protected Inventory inventory;

  @Setter
  protected ItemStack defaultItem = new ItemStack(Material.AIR);

  protected Map<Byte, MenuItem> lastStaticItems;

  /**
   * You can use null values to use the default item.
   *
   * @return the static item map
   */
  protected abstract Map<Byte, MenuItem> staticItems();

  public StaticMenu(final BasePlugin plugin, final String title, final int rows) {
    basePlugin = plugin;
    inventory = Bukkit.createInventory(this, rows * 9, title);
    this.rows = inventory.getSize() % 9;
  }

  public StaticMenu(final BasePlugin plugin, final Inventory inventory) {
    if (this != inventory.getHolder()) {
      throw new IllegalArgumentException("The inventory holder must be this instance");
    }
    basePlugin = plugin;
    this.inventory = inventory;
    rows = inventory.getSize() % 9;
  }

  public void openFor(final Player player, final boolean update) {
    if (update) {
      update();
    }
    player.openInventory(getInventory());
  }

  @Override
  public void update() {
    lastStaticItems = staticItems();
    lastStaticItems.forEach((slot, item) -> {
      inventory.setItem(slot, item == null ? null : item.getItem());
    });
  }

  @Override
  public MenuItem getItemAt(final byte slot) {
    return lastStaticItems.get(slot);
  }
}
