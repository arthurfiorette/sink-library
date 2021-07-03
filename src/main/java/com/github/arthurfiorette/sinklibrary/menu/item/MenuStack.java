package com.github.arthurfiorette.sinklibrary.menu.item;

import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;
import org.bukkit.inventory.ItemStack;

public class MenuStack implements MenuItem {

  private ItemStack item;
  private ClickListener listener;

  public MenuStack(final ItemStack item) {
    this(item, ClickListener.ignore());
  }

  public MenuStack(final ItemStack item, final ClickListener listener) {
    this.item = item;
    this.listener = listener;
  }

  @Override
  public ItemStack getItem() {
    return this.item;
  }

  @Override
  public ClickListener getListener() {
    return this.listener;
  }

  /**
   * The inventory will only update when the next {@link BaseMenu#update()} be
   * called
   */
  public void setItem(final ItemStack item) {
    this.item = item;
  }

  public void setListener(final ClickListener listener) {
    this.listener = listener;
  }
}
