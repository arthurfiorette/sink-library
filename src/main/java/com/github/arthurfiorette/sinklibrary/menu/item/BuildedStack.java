package com.github.arthurfiorette.sinklibrary.menu.item;

import com.github.arthurfiorette.sinklibrary.item.ItemBuilder;
import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;
import org.bukkit.inventory.ItemStack;

public class BuildedStack implements MenuItem {

  private ItemBuilder builder;
  private ClickListener listener;

  public BuildedStack(ItemBuilder builder) {
    this(builder, ClickListener.ignore());
  }

  public BuildedStack(ItemBuilder builder, ClickListener listener) {
    this.builder = builder;
    this.setListener(listener);
  }

  @Override
  public ItemStack getItem() {
    return getBuilder().build();
  }

  @Override
  public ClickListener getListener() {
    return listener;
  }

  public void setListener(ClickListener listener) {
    this.listener = listener;
  }

  public ItemBuilder getBuilder() {
    return builder;
  }

  /**
   * The inventory will only update when the next {@link BaseMenu#update()} be
   * called
   */
  public void setBuilder(ItemBuilder builder) {
    this.builder = builder;
  }
}
