package com.github.arthurfiorette.sinklibrary.menu.item;

import org.bukkit.inventory.ItemStack;

import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;

public interface MenuItem {
  ItemStack getItem();

  default ClickListener getListener() {
    return ClickListener.ignore();
  }
}
