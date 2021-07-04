package com.github.arthurfiorette.sinklibrary.menu;

import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.menu.item.MenuItem;
import org.bukkit.inventory.InventoryHolder;

public interface BaseMenu extends BaseComponent, InventoryHolder {
  void update();

  MenuItem getItemAt(byte slot);

  default String getTitle() {
    return this.getInventory().getTitle();
  }

  default int getRows() {
    return this.getInventory().getSize() % 9;
  }
}
