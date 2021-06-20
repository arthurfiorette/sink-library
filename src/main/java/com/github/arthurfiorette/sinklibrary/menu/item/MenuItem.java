package com.github.arthurfiorette.sinklibrary.menu.item;

import org.bukkit.inventory.ItemStack;

import com.github.arthurfiorette.sinklibrary.menu.actions.ClickAction;
import com.github.arthurfiorette.sinklibrary.services.SpigotService;

/**
 * Represents a stack of items to be in a SinkMenu
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public class MenuItem {

  private final ItemStack itemStack;
  private final ClickAction clickAction;
  private int column = -1;
  private int row = -1;

  /**
   * Constructs a new MenuItem
   *
   * @param item the itemstack copy
   */
  public MenuItem(ItemStack item) {
    this(item, ClickAction.ignored());
  }

  /**
   * Constructs a new MenuItem with a clickAction
   *
   * @param item the itemstack copy
   * @param action the action when clicked
   */
  public MenuItem(ItemStack item, ClickAction action) {
    itemStack = item;
    clickAction = action;
  }

  /**
   * @return the item stack
   */
  public ItemStack getItemStack() {
    return itemStack;
  }

  /**
   * @return the click action
   */
  public ClickAction getClickAction() {
    return clickAction;
  }

  /**
   * @return the column where the item is located in or -1 if it was not
   * explicitly defined
   */
  public int getColumn() {
    return column;
  }

  /**
   * @return the row where the item is located in or -1 if it was not explicitly
   * defined
   */
  public int getRow() {
    return row;
  }

  /**
   * @return the slot where the item is located in or -1 if it was not
   * explicitly defined
   */
  public int getSlot() {
    return row == -1 || column == -1 ? -1 : SpigotService.getChestSlot(column, row);
  }

  /**
   * Changes the location information for this column
   *
   * @param column the new column
   *
   * @return itself
   */
  public MenuItem setColumn(int column) {
    this.column = column;
    return this;
  }

  /**
   * Changes the location information for this row
   *
   * @param row the new row
   *
   * @return itself
   */
  public MenuItem setRow(int row) {
    this.row = row;
    return this;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((clickAction == null) ? 0 : clickAction.hashCode());
    result = prime * result + column;
    result = prime * result + ((itemStack == null) ? 0 : itemStack.hashCode());
    result = prime * result + row;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof MenuItem)) {
      return false;
    }
    MenuItem other = (MenuItem) obj;
    if (clickAction == null) {
      if (other.clickAction != null) {
        return false;
      }
    } else if (!clickAction.equals(other.clickAction)) {
      return false;
    }
    if (column != other.column) {
      return false;
    }
    if (itemStack == null) {
      if (other.itemStack != null) {
        return false;
      }
    } else if (!itemStack.equals(other.itemStack)) {
      return false;
    }
    if (row != other.row) {
      return false;
    }
    return true;
  }
}
