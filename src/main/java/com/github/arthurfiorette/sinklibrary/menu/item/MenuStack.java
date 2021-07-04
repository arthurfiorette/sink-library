package com.github.arthurfiorette.sinklibrary.menu.item;

import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class MenuStack implements MenuItem {

  @Getter
  @NonNull
  private ItemStack item;

  @Getter
  @Setter
  @NonNull
  private ClickListener listener;

  public MenuStack(final ItemStack item) {
    this(item, ClickListener.ignore());
  }

  /**
   * The inventory will only update when the next {@link BaseMenu#update()} be
   * called
   */
  public void setItem(final ItemStack item) {
    this.item = item;
  }

}
