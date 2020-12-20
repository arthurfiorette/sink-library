package com.github.hazork.sinkspigot.menu.item;

import org.bukkit.inventory.ItemStack;

import com.github.hazork.sinkspigot.menu.actions.ClickAction;
import com.github.hazork.sinkspigot.services.utils.SpigotServices;

public class MenuItem {

    private final ItemStack itemStack;
    private final ClickAction clickAction;
    private int column = -1;
    private int row = -1;

    public MenuItem(ItemStack item) {
	this(item, ClickAction.ignore());
    }

    public MenuItem(ItemStack item, ClickAction action) {
	itemStack = item;
	clickAction = action;
    }

    public ItemStack getItemStack() {
	return itemStack;
    }

    public ClickAction getClickAction() {
	return clickAction;
    }

    public int getColumn() {
	return column;
    }

    public int getRow() {
	return row;
    }

    public int getSlot() {
	return SpigotServices.getChestSlot(column, row);
    }

    public MenuItem setColumn(int column) {
	this.column = column;
	return this;
    }

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
