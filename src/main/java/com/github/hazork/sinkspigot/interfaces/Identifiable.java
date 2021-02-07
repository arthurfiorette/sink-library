package com.github.hazork.sinkspigot.interfaces;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * This class is used to help any objects that are relationated with
 * {@link org.bukkit.entity.Player}, as Storing them with the Player object
 * isn't safety, a {@link java.util.UUID} should be used istead.
 * 
 * @author https://github.com/Hazork/sink-library/
 */
public interface Identifiable {

    /**
     * @return this instance {@link java.util.UUID}
     */
    UUID getUniqueId();

    /**
     * @return the OfflinePlayer linked with this unique id.
     */
    default OfflinePlayer asOfflinePlayer() {
	return Bukkit.getOfflinePlayer(getUniqueId());
    }

    /**
     * @return an optional that contain a player with this unique id if he is
     * online. This sould be verified with isOnline() method.
     */
    default Optional<Player> asPlayer() {
	return Optional.ofNullable(Bukkit.getPlayer(getUniqueId()));
    }

    /**
     * @return the name of this player.
     */
    default String getName() {
	return asOfflinePlayer().getName();
    }

    /**
     * @return {@code true} if this player is online.
     */
    default boolean isOnline() {
	return asOfflinePlayer().isOnline();
    }
}
