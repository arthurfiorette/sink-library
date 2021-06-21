package com.github.arthurfiorette.sinklibrary.menu.item;

import com.github.arthurfiorette.sinklibrary.menu.actions.ClickAction;
import com.github.arthurfiorette.sinklibrary.services.SpigotService;
import java.util.Objects;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a stack of items to be in a SinkMenu
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public class MenuItem {

  protected final ItemStack itemStack;
  protected final ClickAction clickAction;
  protected int column = -1;
  protected int row = -1;

  /**
   * Constructs a new MenuItem
   *
   * @param item the ItemStack copy
   */
  public MenuItem(ItemStack item) {
    this(item, ClickAction.ignored());
  }

  /**
   * Constructs a new MenuItem with a clickAction
   *
   * @param item the ItemStack copy
   * @param action the action when clicked
   */
  public MenuItem(ItemStack item, ClickAction action) {
    this.itemStack = item;
    this.clickAction = action;
  }

  /**
   * @return the item stack
   */
  public ItemStack getItemStack() {
    return this.itemStack;
  }

  /**
   * @return the click action
   */
  public ClickAction getClickAction() {
    return this.clickAction;
  }

  /**
   * @return the column where the item is located in or -1 if it was not
   * explicitly defined
   */
  public int getColumn() {
    return this.column;
  }

  /**
   * @return the row where the item is located in or -1 if it was not explicitly
   * defined
   */
  public int getRow() {
    return this.row;
  }

  /**
   * @return the slot where the item is located in or -1 if it was not
   * explicitly defined
   */
  public int getSlot() {
    return this.row == -1 || this.column == -1
      ? -1
      : SpigotService.getChestSlot(this.column, this.row);
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
    return Objects.hash(clickAction, column, itemStack, row);
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
    if (!Objects.equals(this.clickAction, other.clickAction)) {
      return false;
    }
    if (this.column != other.column) {
      return false;
    }
    if (!Objects.equals(this.itemStack, other.itemStack)) {
      return false;
    }
    if (this.row != other.row) {
      return false;
    }
    return true;
  }
}
