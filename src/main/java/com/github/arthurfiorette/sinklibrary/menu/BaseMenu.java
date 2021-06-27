package com.github.arthurfiorette.sinklibrary.menu;

import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.menu.item.MenuItem;
import org.bukkit.inventory.InventoryHolder;

public interface BaseMenu extends BaseComponent, InventoryHolder {
  void update();

  public MenuItem getItemAt(byte slot);

  default String getTitle() {
    return getInventory().getTitle();
  }

  default int getRows() {
    return getInventory().getSize() % 9;
  }
}
