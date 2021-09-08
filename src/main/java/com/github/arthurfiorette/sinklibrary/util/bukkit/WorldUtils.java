package com.github.arthurfiorette.sinklibrary.util.bukkit;

import com.cryptomorin.xseries.XItemStack;
import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class WorldUtils {

  /**
   * Give items to a player, but if he doesn't have enough space for all the
   * items, what is left will be dropped on the floor.
   *
   * @param player the player to receive the items
   * @param items the items to give
   *
   * @deprecated Use {@link XItemStack#giveOrDrop(Player, ItemStack...)}
   */
  @Deprecated
  public void give(final Player player, final ItemStack... items) {
    XItemStack.giveOrDrop(player, items);
  }

  /**
   * Drop any ItemStack in a specific location.
   *
   * @param location the location to drop
   * @param items the items to give
   */
  public void drop(final Location location, final ItemStack... items) {
    final World world = location.getWorld();

    for (final ItemStack item : items) {
      world.dropItem(location, item);
    }
  }

  /**
   * Drop any ItemStack naturally in a specific location.
   *
   * @param location the location to drop
   * @param items the items to give
   */
  public void dropNaturally(final Location location, final ItemStack... items) {
    final World world = location.getWorld();

    for (final ItemStack item : items) {
      world.dropItemNaturally(location, item);
    }
  }

  /**
   * Play the sound for the players
   *
   * @param sound the sound to play
   * @param players the players to play the sound
   */
  public void play(final Sound sound, final Player... players) {
    for (final Player player : players) {
      player.playSound(player.getLocation(), sound, 3.0F, 0.5F);
    }
  }

  /**
   * Play the sound for the players
   *
   * @param sound the sound to play
   * @param players the players to play the sound
   */
  public void play(final String sound, final Player... players) {
    for (final Player player : players) {
      player.playSound(player.getLocation(), sound, 3.0F, 0.5F);
    }
  }
}
