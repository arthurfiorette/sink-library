package com.github.arthurfiorette.sinklibrary.menu.item;

import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;
import org.bukkit.inventory.ItemStack;

public interface MenuItem {
  ItemStack getItem();

  default <T extends BaseMenu> ClickListener<T> getListener() {
    return ClickListener.ignore();
  }
}
