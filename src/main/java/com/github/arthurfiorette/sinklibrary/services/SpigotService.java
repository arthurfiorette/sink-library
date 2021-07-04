package com.github.arthurfiorette.sinklibrary.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.experimental.UtilityClass;

/**
 * A service class that handles with spigot.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
@UtilityClass
public final class SpigotService {

  /**
   * Call a {@link Event}
   *
   * @param event the event to be called.
   */
  public void callEvent(final Event event) {
    Bukkit.getPluginManager().callEvent(event);
  }

  /**
   * Give items to a player, but if he doesn't have enough space for all the
   * items, what is left will be dropped on the floor.
   *
   * @param player the player to receive the items
   * @param items the items to give
   */
  public void giveItens(final Player player, final ItemStack... items) {
    final HashMap<Integer, ItemStack> remainders = player.getInventory().addItem(items);
    if (!remainders.isEmpty()) {
      remainders.values().stream().forEach(i -> SpigotService.dropItens(player.getLocation(), i));
    }
  }

  /**
   * Drop any ItemStack in a specific location.
   *
   * @param location the location to drop
   * @param items the items to give
   */
  public void dropItens(final Location location, final ItemStack... items) {
    final World world = location.getWorld();
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
  public boolean hasPlugin(final String name) {
    return Bukkit.getPluginManager().isPluginEnabled(name);
  }

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
  public boolean isMinecraftPack(final long amount) {
    return (amount > 0) && (amount <= 64);
  }

  /**
   * Checks if the object is a player.
   *
   * @param obj any object that can be a player
   *
   * @return true if it's an instance of player
   */
  public boolean isPlayer(final Object obj) {
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
  public static long asTicks(final TimeUnit unit, final long value) {
    return SpigotService.asTicks(unit.toMillis(value));
  }

  /**
   * Convert milliseconds to minecraft ticks.
   * <p>
   * {@code milliseconds / 50 = ticks}
   *
   * @param milliseconds the time in milliseconds
   *
   * @return the amount of ticks into this time
   */
  public long asTicks(final long milliseconds) {
    return milliseconds / 50;
  }

  /**
   * Convert any text using {@literal &} as color code to minecraft colored text
   * {@literal §}.
   *
   * @param text the raw text
   *
   * @return the colored text
   */
  public String setColors(final String text) {
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
  public int getChestSlot(int column, int row) {
    row = row <= 0 ? 1 : row > 6 ? 6 : row;
    column = column <= 0 ? 1 : column > 9 ? 9 : column;
    return ((9 * (row - 1)) + column) - 1;
  }

  /**
   * Play the sound for the players
   *
   * @param sound the sound to play
   * @param players the players to play the sound
   */
  public void playSound(final Sound sound, final Player... players) {
    for(final Player p: players) {
      p.playSound(p.getLocation(), sound, 3.0F, 0.5F);
    }
  }

  /**
   * Update this item stack meta.
   *
   * @param item the item stack to update
   * @param callback the callback to edit the meta
   */
  public void updateItemMeta(final ItemStack item, final Consumer<ItemMeta> callback) {
    final ItemMeta meta = item.getItemMeta();
    callback.accept(meta);
    item.setItemMeta(meta);
  }
}
