package com.github.arthurfiorette.sinklibrary.interfaces;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.github.arthurfiorette.sinklibrary.uuid.FastUuid;

/**
 * This class is used to help any objects that are  with
 * {@link org.bukkit.entity.Player} or {@link java.util.UUID},related as Storing them
 * with the Player object isn't safety, a {@link java.util.UUID} should be used
 * instead.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public interface Identifiable {
  /**
   * @return this instance {@link java.util.UUID}
   */
  UUID getUniqueId();

  /**
   * @return this uuid stringified.
   */
  default String toIdString() {
    return FastUuid.toString(this.getUniqueId());
  }

  /**
   * @return the OfflinePlayer linked with this unique id.
   */
  default OfflinePlayer asOfflinePlayer() {
    return Bukkit.getOfflinePlayer(this.getUniqueId());
  }

  /**
   * @return an optional that contain a player with this unique id if he is
   * online. This should be verified with isOnline() method.
   */
  default Optional<Player> asPlayer() {
    return Optional.ofNullable(Bukkit.getPlayer(this.getUniqueId()));
  }

  /**
   * @return the name of this player.
   */
  default String getName() {
    return this.asOfflinePlayer().getName();
  }

  /**
   * @return {@code true} if this player is online.
   */
  default boolean isOnline() {
    return this.asOfflinePlayer().isOnline();
  }
}
