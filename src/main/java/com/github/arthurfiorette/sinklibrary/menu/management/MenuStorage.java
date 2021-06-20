package com.github.arthurfiorette.sinklibrary.menu.management;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import com.github.arthurfiorette.sinklibrary.menu.SinkMenu;

public abstract class MenuStorage<M extends Enum<M> & MenuFactory> implements BaseService {

  protected final Map<UUID, EnumMap<M, SinkMenu>> inventories = new HashMap<>();
  protected final MenuListener listener;
  protected final BasePlugin plugin;
  protected final Class<M> clazz;

  protected MenuStorage(BasePlugin plugin, Class<M> clazz) {
    this.listener = new MenuListener(plugin);
    this.plugin = plugin;
    this.clazz = clazz;
  }

  @Override
  public BasePlugin getPlugin() {
    return plugin;
  }

  @Override
  public void enable() {
    this.listener.enable();
  }

  @Override
  public void disable() {
    this.listener.disable();
  }

  @SuppressWarnings("unchecked")
  public <I extends SinkMenu> I get(Player player, M menu) {
    EnumMap<M, SinkMenu> invs = inventories.get(player.getUniqueId());

    if (invs == null) {
      invs = new EnumMap<>(clazz);
      inventories.put(player.getUniqueId(), invs);
    }

    SinkMenu sinkMenu = invs.get(menu);

    if (sinkMenu == null) {
      sinkMenu = menu.create(plugin, player);
      invs.put(menu, sinkMenu);
    }

    return (I) sinkMenu;
  }

}
