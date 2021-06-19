package br.com.arthurfiorette.sinklibrary.menu.listener;

import br.com.arthurfiorette.sinklibrary.SinkPlugin;
import br.com.arthurfiorette.sinklibrary.listener.SinkListener;
import br.com.arthurfiorette.sinklibrary.menu.SinkMenu;
import br.com.arthurfiorette.sinklibrary.menu.item.MenuItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class is instantiated to handle all listeners for all menus in a
 * specified plugin.
 *
 * @author https://github.com/Hazork/sink-library/
 */
public final class MenuListener extends SinkListener {

  public MenuListener(SinkPlugin owner) {
    super(owner);
  }

  @Override
  @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
  public void onInventoryClick(InventoryClickEvent event) {
    Inventory inv = event.getInventory();
    if (inv.getHolder() instanceof SinkMenu) {
      SinkMenu menu = (SinkMenu) inv.getHolder();
      if (menu.getPlugin().equals(this.getPlugin())) {
        event.setCancelled(true);
        InventoryAction action = event.getAction();
        int slot = event.getRawSlot();
        if (slot < inv.getSize() && action != InventoryAction.NOTHING) {
          MenuItem item = menu.getItems().get(slot);
          if (item != null) {
            item.getClickAction().click(event.getCurrentItem(), action);
          }
        }
      }
    }
  }
}
