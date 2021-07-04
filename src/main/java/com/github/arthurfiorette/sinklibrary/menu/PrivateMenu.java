package com.github.arthurfiorette.sinklibrary.menu;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class PrivateMenu extends StaticMenu {

  @Getter
  protected Player owner;

  public PrivateMenu(
    final BasePlugin plugin,
    final Player owner,
    final String title,
    final int rows
  ) {
    super(plugin, title, rows);
    this.owner = owner;
  }

  public PrivateMenu(final BasePlugin plugin, final Player owner, final Inventory inventory) {
    super(plugin, inventory);
    this.owner = owner;
  }

  /**
   * {@inheritDoc}
   * <p>
   * This menu is intended to be a private menu, use with care
   */
  @Override
  public void openFor(final Player player, final boolean update) {
    super.openFor(player, update);
  }

  public void open(final boolean update) {
    super.openFor(this.owner, update);
  }
}
