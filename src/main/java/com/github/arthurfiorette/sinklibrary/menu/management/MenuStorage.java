package com.github.arthurfiorette.sinklibrary.menu.management;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;

public class MenuStorage<M extends Enum<M> & MenuFactory> implements BaseService {

  protected final Map<UUID, EnumMap<M, BaseMenu>> inventories = new HashMap<>();
  protected final MenuListener listener;
  protected final BasePlugin plugin;
  protected final Class<M> clazz;

  protected MenuStorage(BasePlugin plugin, Class<M> clazz) {
    this.listener = new MenuListener(plugin);
    this.plugin = plugin;
    this.clazz = clazz;
  }

  @SuppressWarnings("unchecked")
  public <I extends BaseMenu> I get(Player player, M menu) {
    EnumMap<M, BaseMenu> invs = this.inventories.get(player.getUniqueId());

    if (invs == null) {
      invs = new EnumMap<>(this.clazz);
      this.inventories.put(player.getUniqueId(), invs);
    }

    BaseMenu baseMenu = invs.get(menu);

    if (baseMenu == null) {
      baseMenu = menu.create(this.plugin, player);
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
  }

  public BasePlugin getPlugin() {
    return plugin;
  }
}
