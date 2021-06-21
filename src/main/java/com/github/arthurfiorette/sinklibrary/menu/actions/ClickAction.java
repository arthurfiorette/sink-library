package com.github.arthurfiorette.sinklibrary.menu.actions;

import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

/**
 * This interface represents a inventory click action.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
@FunctionalInterface
public interface ClickAction {
  /**
   * Handles a inventory click.
   *
   * @param type the mouse click type
   * @param item the clicked item
   */
  void click(ItemStack item, MouseClick type);

  /**
   * Handles a inventory click.
   *
   * @param action the inventory action type
   */
  default void click(ItemStack item, InventoryAction action) {
    this.click(item, MouseClick.from(action));
  }

  /**
   * A click action that does nothing.
   *
   * @return the ClickAction
   */
  static ClickAction ignored() {
    return (item, type) -> {};
  }
}
