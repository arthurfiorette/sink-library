package com.github.hazork.sinklibrary.services.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public final class SpigotServices {

    private SpigotServices() {}

    public static void callEvent(Event event) {
	Bukkit.getPluginManager().callEvent(event);
    }

    public static void giveItens(Player player, ItemStack... itens) {
	HashMap<Integer, ItemStack> remainders = player.getInventory().addItem(itens);
	if (!remainders.isEmpty()) {
	    remainders.values().stream().forEach(i -> dropItens(player.getLocation(), i));
	}
    }

    public static void dropItens(Location location, ItemStack... itens) {
	World world = location.getWorld();
	Arrays.stream(itens).forEach(i -> world.dropItemNaturally(location, i));
    }

    public static boolean hasPlugin(String name) {
	return Bukkit.getPluginManager().isPluginEnabled(name);
    }

    public static boolean hasEmptySlot(Player player) {
	return player.getInventory().firstEmpty() != -1;
    }

    public static boolean isMinecraftPack(long amount) {
	return (amount > 0 && amount <= 64);
    }

    public static boolean isPlayer(Object obj) {
	return obj instanceof Player;
    }

    public static long asTicks(TimeUnit unit, long value) {
	return asTicks(unit.toMillis(value));
    }

    public static long asTicks(long miliseconds) {
	return miliseconds / 50;
    }

}
