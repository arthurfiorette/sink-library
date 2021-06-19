package com.github.arthurfiorette.sinklibrary.services.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

/**
 * A service class that handles with spigot.
 *
 * @author https://github.com/Hazork/sink-library/
 */
public final class SpigotServices {

  /**
   * A private constructor prevent callers from accidentally instantiating an
   * instance.
   */
  private SpigotServices() {}

  /**
   * Call a {@link Event}
   *
   * @param event the event to be called.
   */
  public static void callEvent(Event event) {
    Bukkit.getPluginManager().callEvent(event);
  }

  /**
   * Give items to a player, but if he doesn't have enough space for all the
   * items, what is left will be dropped on the floor.
   *
   * @param player the player to receive the items
   * @param items the items to give
   */
  public static void giveItens(Player player, ItemStack... items) {
    HashMap<Integer, ItemStack> remainders = player.getInventory().addItem(items);
    if (!remainders.isEmpty()) {
      remainders.values().stream().forEach(i -> dropItens(player.getLocation(), i));
    }
  }

  /**
   * Drop any ItemStack in a specific location.
   *
   * @param location the location to drop
   * @param items the items to give
   */
  public static void dropItens(Location location, ItemStack... items) {
    World world = location.getWorld();
    Arrays.stream(items).forEach(i -> world.dropItemNaturally(location, i));
  }

  /**
   * Checks if this runtime has a plugin loaded with this same name. Useful to
   * check if can use the plugin api.
   *
   * @param name the plugin name
   *
   * @return true if this plugin is enabled
   */
  public static boolean hasPlugin(String name) {
    return Bukkit.getPluginManager().isPluginEnabled(name);
  }

  /**
   * Checks if the player has at least 1 empty slot in his inventory.
   *
   * @param player the player to check
   *
   * @return true if the player inventory has empty slots
   */
  public static boolean hasEmptySlot(Player player) {
    return player.getInventory().firstEmpty() != -1;
  }

  /**
   * Check if the amount isn't lower than 0 and greather than 64.
   *
   * @param amount the amount.
   *
   * @return true if it fits between 0 and 64
   */
  public static boolean isMinecraftPack(long amount) {
    return (amount > 0 && amount <= 64);
  }

  /**
   * Checks if the object is a player.
   *
   * @param obj any object that can be a player
   *
   * @return true if it's an instance of player
   */
  public static boolean isPlayer(Object obj) {
    return obj instanceof Player;
  }

  /**
   * Convert any time to minecraft ticks time.
   *
   * @param unit the {@link TimeUnit}
   * @param value the time value
   *
   * @return the amount of ticks into this time
   */
  public static long asTicks(TimeUnit unit, long value) {
    return asTicks(unit.toMillis(value));
  }

  /**
   * Convert miliseconds to minecraft ticks.
   * <p>
   * {@code miliseconds / 50 = ticks}
   *
   * @param miliseconds the time in miliseconds
   *
   * @return the amount of ticks into this time
   */
  public static long asTicks(long miliseconds) {
    return miliseconds / 50;
  }

  /**
   * Convert any text using {@literal &} as color code to minecraft colored text
   * {@literal §}.
   *
   * @param text the raw text
   *
   * @return the colored text
   */
  public static String setColors(String text) {
    return ChatColor.translateAlternateColorCodes('&', text);
  }

  /**
   * Convert any coordinate to minecraft chest slot value.
   *
   * @param column the column
   * @param row the row.
   *
   * @return the slot value based on the column and row
   */
  public static int getChestSlot(int column, int row) {
    row = (row <= 0) ? 1 : (row > 6) ? 6 : row;
    column = (column <= 0) ? 1 : (column > 9) ? 9 : column;
    return 9 * (row - 1) + column - 1;
  }

  /**
   * Play the sound for the players
   *
   * @param sound the sound to play
   * @param players the players to play the sound
   */
  public static void playSound(Sound sound, Player... players) {
    for (Player p : players) {
      p.playSound(p.getLocation(), sound, 3.0F, 0.5F);
    }
  }
}
