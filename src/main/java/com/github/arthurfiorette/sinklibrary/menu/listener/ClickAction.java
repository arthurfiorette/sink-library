package com.github.arthurfiorette.sinklibrary.menu.listener;

import org.bukkit.event.inventory.InventoryAction;

/**
 * This enum is a better representation of a mouse click in a inventory.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public enum ClickAction {
  /**
   * When the click is in the left mouse button.
   */
  LEFT,
  /**
   * When the click is in the left mouse button while pressing shift.
   */
  SHIFT_LEFT,
  /**
   * When the click is in the right mouse button.
   */
  RIGHT,
  /**
   * When the click is in the keyboard drop button.
   */
  DROP,
  /**
   * When the click is in the scroll mouse button.
   */
  SCROLL,
  /**
   * When the click is in other button.
   */
  OTHER;

  /**
   * Transforms a InventoryClick into a MouseClick
   *
   * @param action the inventory action
   *
   * @return the MouseClick from this action
   */
  public static ClickAction from(final InventoryAction action) {
    switch (action) {
      case PICKUP_ALL:
      case PLACE_ALL:
      case PLACE_SOME:
      case SWAP_WITH_CURSOR:
        return ClickAction.LEFT;
      case PICKUP_HALF:
      case PLACE_ONE:
        return ClickAction.RIGHT;
      case MOVE_TO_OTHER_INVENTORY:
        return ClickAction.SHIFT_LEFT;
      case DROP_ALL_CURSOR:
      case DROP_ALL_SLOT:
      case DROP_ONE_CURSOR:
      case DROP_ONE_SLOT:
        return ClickAction.DROP;
      case CLONE_STACK:
        return ClickAction.SCROLL;
      default:
        return ClickAction.OTHER;
    }
  }
}
