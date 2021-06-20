package com.github.arthurfiorette.sinklibrary.menu.actions;

import org.bukkit.event.inventory.InventoryAction;

/**
 * This enum is a better representation of a mouse click in a inventory.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public enum MouseClick {
  /**
   * When the click is in the left mouse buton.
   */
  LEFT,
  /**
   * When the click is in the left mouse buton while pressing shift.
   */
  SHIFT_LEFT,
  /**
   * When the click is in the right mouse buton.
   */
  RIGHT,
  /**
   * When the click is in the keyboard drop buton.
   */
  DROP,
  /**
   * When the click is in the scroll mouse buton.
   */
  SCROLL,
  /**
   * When the click is in other buton.
   */
  OTHER;

  /**
   * Transforms a InventoryClick into a MouseClick
   *
   * @param action the inventory action
   *
   * @return the mouseclick from this action
   */
  public static MouseClick from(InventoryAction action) {
    switch (action) {
      case PICKUP_ALL:
      case PLACE_ALL:
      case PLACE_SOME:
      case SWAP_WITH_CURSOR:
        return MouseClick.LEFT;

      case PICKUP_HALF:
      case PLACE_ONE:
        return MouseClick.RIGHT;

      case MOVE_TO_OTHER_INVENTORY:
        return MouseClick.SHIFT_LEFT;

      case DROP_ALL_CURSOR:
      case DROP_ALL_SLOT:
      case DROP_ONE_CURSOR:
      case DROP_ONE_SLOT:
        return MouseClick.DROP;

      case CLONE_STACK:
        return MouseClick.SCROLL;

      default:
        return MouseClick.OTHER;
    }
  }
}
