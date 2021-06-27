package com.github.arthurfiorette.sinklibrary.menu.listener;

import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface ClickListener<T extends BaseMenu> {
  /**
   * Handles a inventory click.
   *
   * @param type the mouse click type
   * @param item the clicked item
   */
  void onClick(T menu, ItemStack item, ClickAction type);

  /**
   * Handles a inventory click.
   *
   * @param action the inventory action type
   */
  default void onClick(T menu, ItemStack item, InventoryAction action) {
    this.onClick(menu, item, ClickAction.from(action));
  }

  /**
   * A click action that does nothing.
   *
   * @return the ClickAction
   */
  static <T extends BaseMenu> ClickListener<T> ignore() {
    return (menu, item, type) -> {};
  }
}
