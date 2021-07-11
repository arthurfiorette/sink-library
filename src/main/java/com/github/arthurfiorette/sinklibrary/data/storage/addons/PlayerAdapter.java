package com.github.arthurfiorette.sinklibrary.data.storage.addons;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;

import com.github.arthurfiorette.sinklibrary.data.storage.Storage;

public interface PlayerAdapter<V, R> extends Storage<UUID, V, R> {

  default CompletableFuture<V> get(final Player player) {
    return this.get(player.getUniqueId());
  }

  default V getSync(final Player player) {
    return this.getSync(player.getUniqueId());
  }

  default CompletableFuture<Collection<V>> getMany(final Collection<Player> players) {
    return this.getMany(players.stream().map(Player::getUniqueId).collect(Collectors.toSet()));
  }

  default Collection<V> getManySync(final Collection<Player> players) {
    return this.getManySync(players.stream().map(Player::getUniqueId).collect(Collectors.toSet()));
  }

}
