package com.github.hazork.sinkspigot.menu.actions;

import org.bukkit.event.inventory.InventoryAction;

@FunctionalInterface
public interface ClickAction {

    void click(MouseClick type);

    default void click(InventoryAction action) {
	click(MouseClick.from(action));
    }

    static ClickAction ignore() {
	return ignore -> {};
    }

}
