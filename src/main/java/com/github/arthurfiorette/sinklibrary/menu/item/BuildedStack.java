package com.github.arthurfiorette.sinklibrary.menu.item;

import org.bukkit.inventory.ItemStack;

import com.github.arthurfiorette.sinklibrary.item.ItemBuilder;
import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class BuildedStack implements MenuItem {

  @Getter
  @NonNull
  private ItemBuilder builder;

  @Getter
  @Setter
  @NonNull
  private ClickListener listener;

  public BuildedStack(final ItemBuilder builder) {
    this(builder, ClickListener.ignore());
  }

  @Override
  public ItemStack getItem() {
    return this.builder.build();
  }

  /**
   * The inventory will only update when the next {@link BaseMenu#update()} be
   * called
   */
  public void setBuilder(final ItemBuilder builder) {
    this.builder = builder;
  }
}
