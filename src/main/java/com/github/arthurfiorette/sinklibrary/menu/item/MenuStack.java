package com.github.arthurfiorette.sinklibrary.menu.item;

import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;
import lombok.*;
import org.bukkit.inventory.ItemStack;

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
   *
   * @param item the item stack
   */
  public void setItem(final ItemStack item) {
    this.item = item;
  }
}
