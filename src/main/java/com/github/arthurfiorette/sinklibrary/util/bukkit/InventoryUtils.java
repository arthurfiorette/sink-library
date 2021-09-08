package com.github.arthurfiorette.sinklibrary.util.bukkit;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

@UtilityClass
public class InventoryUtils {

  /**
   * Checks if the player has at least 1 empty slot in his inventory.
   *
   * @param player the player to check
   *
   * @return true if the player inventory has empty slots
   */
  public boolean hasEmptySlot(final Player player) {
    return player.getInventory().firstEmpty() != -1;
  }

  /**
   * Check if the amount isn't lower than 0 and greater than 64.
   *
   * @param amount the amount.
   *
   * @return true if it fits between 0 and 64
   */
  public boolean isPack(final int amount) {
    return amount > 0 && amount <= 64;
  }

  /**
   * Convert any coordinate to minecraft chest slot value.
   *
   * @param column the column
   * @param row the row.
   *
   * @return the slot value based on the column and row
   */
  public int slotIndex(int column, int row) {
    // Range 1-6
    row = row <= 0 ? 1 : row > 6 ? 6 : row;
    // Range 1-9
    column = column <= 0 ? 1 : column > 9 ? 9 : column;

    return 9 * (row - 1) + column - 1;
  }
}
