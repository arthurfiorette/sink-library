package com.github.arthurfiorette.sinklibrary.menu.item;

import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;
import org.bukkit.inventory.ItemStack;

public interface MenuItem {
  ItemStack getItem();

  ClickListener getListener();
}
