package com.github.hazork.sinkspigot.menu.actions;

import org.bukkit.event.inventory.InventoryAction;

/**
 * This interface represents a inventory click action.
 *
 * @author https://github.com/Hazork/sink-library/
 */
@FunctionalInterface
public interface ClickAction {

    /**
     * Handles a inventory click.
     *
     * @param type the mouse click type
     */
    void click(MouseClick type);

    /**
     * Handles a inventory click.
     *
     * @param action the inventory action type
     */
    default void click(InventoryAction action) {
	this.click(MouseClick.from(action));
    }

    /**
     * A click action that does nothing.
     *
     * @return the clickaction
     */
    static ClickAction ignore() {
	return ignore -> {};
    }

}
