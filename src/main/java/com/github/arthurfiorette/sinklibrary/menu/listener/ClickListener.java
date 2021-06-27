package com.github.arthurfiorette.sinklibrary.menu.listener;

import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface ClickListener {
  /**
   * Handles a inventory click.
   *
   * @param type the mouse click type
   * @param item the clicked item
   */
  void onClick(ItemStack item, ClickAction type);

  /**
   * Handles a inventory click.
   *
   * @param action the inventory action type
   */
  default void onClick(ItemStack item, InventoryAction action) {
    this.onClick(item, ClickAction.from(action));
  }

  /**
   * A click action that does nothing.
   *
   * @return the ClickAction
   */
  static ClickListener ignore() {
    return (item, type) -> {};
  }
}
