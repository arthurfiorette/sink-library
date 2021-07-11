package com.github.arthurfiorette.sinklibrary.data.storage.addons;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;

import com.github.arthurfiorette.sinklibrary.data.storage.Storage;

public interface PlayerAdapter<V, R> extends Storage<UUID, V, R> {

  default CompletableFuture<V> get(Player player) {
    return get(player.getUniqueId());
  }

  default V getSync(Player player) {
    return getSync(player.getUniqueId());
  }

  default CompletableFuture<Collection<V>> getMany(Collection<Player> players) {
    return getMany(players.stream().map(Player::getUniqueId).collect(Collectors.toSet()));
  }

  default Collection<V> getManySync(Collection<Player> players) {
    return getManySync(players.stream().map(Player::getUniqueId).collect(Collectors.toSet()));
  }

}
