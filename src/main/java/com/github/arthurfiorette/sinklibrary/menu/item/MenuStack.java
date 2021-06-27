package com.github.arthurfiorette.sinklibrary.menu.item;

import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;
import org.bukkit.inventory.ItemStack;

public class MenuStack<T extends BaseMenu> implements MenuItem {

  private ItemStack item;
  private ClickListener listener;

  public MenuStack(ItemStack item) {
    this(item, ClickListener.ignore());
  }

  public MenuStack(ItemStack item, ClickListener listener) {
    this.item = item;
    this.listener = listener;
  }

  @Override
  public ItemStack getItem() {
    return item;
  }

  @Override
  public ClickListener getListener() {
    return listener;
  }

  /**
   * The inventory will only update when the next {@link BaseMenu#update()} be
   * called
   */
  public void setItem(ItemStack item) {
    this.item = item;
  }

  public void setListener(ClickListener listener) {
    this.listener = listener;
  }
}
