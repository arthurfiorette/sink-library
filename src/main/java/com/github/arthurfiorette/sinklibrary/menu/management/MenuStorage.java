package com.github.arthurfiorette.sinklibrary.menu.management;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.github.arthurfiorette.sinklibrary.core.BaseModule;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;

import lombok.Getter;

public abstract class MenuStorage<M extends Enum<M> & MenuFactory> implements BaseService {

  protected final Map<UUID, EnumMap<M, BaseMenu>> inventories = new HashMap<>();
  protected final MenuListener listener;
  protected final Class<M> clazz;

  @Getter
  protected final BaseModule basePlugin;

  protected MenuStorage(final BasePlugin plugin, final Class<M> clazz) {
    this.listener = new MenuListener(plugin);
    this.basePlugin = plugin;
    this.clazz = clazz;
  }

  @SuppressWarnings("unchecked")
  public <I extends BaseMenu> I get(final Player player, final M menu) {
    EnumMap<M, BaseMenu> invs = this.inventories.get(player.getUniqueId());

    if (invs == null) {
      invs = new EnumMap<>(this.clazz);
      this.inventories.put(player.getUniqueId(), invs);
    }

    BaseMenu baseMenu = invs.get(menu);

    if (baseMenu == null) {
      baseMenu = menu.create(this.basePlugin, player);
      invs.put(menu, baseMenu);
    }

    return (I) baseMenu;
  }

  @Override
  public void enable() throws Exception {
    this.listener.enable();
  }

  @Override
  public void disable() throws Exception {
    this.listener.disable();

    for (final UUID id : this.inventories.keySet()) {
      final Player player = Bukkit.getPlayer(id);

      if (player == null) {
        continue;
      }

      final Inventory inventory = player.getOpenInventory().getTopInventory();

      if (inventory == null || !(inventory.getHolder() instanceof BaseMenu)) {
        continue;
      }

      player.closeInventory();
      player.sendMessage("§cYour inventory was closed because it handler was disabled.");
    }
  }
}
