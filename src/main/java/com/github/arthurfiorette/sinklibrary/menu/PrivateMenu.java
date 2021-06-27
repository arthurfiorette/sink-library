package com.github.arthurfiorette.sinklibrary.menu;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class PrivateMenu extends StaticMenu {

  protected Player owner;

  public PrivateMenu(BasePlugin plugin, Player owner, String title, int rows) {
    super(plugin, title, rows);
    this.owner = owner;
  }

  public PrivateMenu(BasePlugin plugin, Player owner, Inventory inventory) {
    super(plugin, inventory);
    this.owner = owner;
  }

  /**
   * {@inheritDoc}
   * <p>
   * This menu is intended to be a private menu, use with care
   */
  @Override
  public void openFor(Player player, boolean update) {
    super.openFor(player, update);
  }

  public void open(boolean update) {
    super.openFor(owner, update);
  }

  public Player getOwner() {
    return owner;
  }
}
