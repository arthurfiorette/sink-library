package com.github.hazork.sinkspigot.menu.actions;

import org.bukkit.event.inventory.InventoryAction;

public enum MouseClick {

    LEFT,
    SHIFT_LEFT,
    RIGHT,
    DROP,
    SCROLL,
    OTHER;

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
