package br.com.arthurfiorette.sinklibrary.menu;

import br.com.arthurfiorette.sinklibrary.SinkComponent;
import br.com.arthurfiorette.sinklibrary.SinkPlugin;
import br.com.arthurfiorette.sinklibrary.menu.item.MenuItem;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * An minecraft menu with better methods and a fanciest way to handle with.
 *
 * @author https://github.com/Hazork/sink-library/
 */
public abstract class SinkMenu implements InventoryHolder, SinkComponent {

  private final SinkPlugin plugin;
  private final Player player;
  private final String title;
  private final int rows;

  protected Map<Integer, MenuItem> itemMap = new HashMap<>();
  private Inventory inventory;

  /**
   * Constructs a new SinkMenu
   *
   * @param plugin the sink plugin instance
   * @param player the player owner;
   * @param title the inventory title
   * @param rows the number of inventory rows
   */
  public SinkMenu(SinkPlugin plugin, Player player, String title, int rows) {
    this.plugin = plugin;
    this.player = player;
    this.title = title;
    this.rows = rows;
    plugin.setupMenus();
  }

  /**
   * @return the MenuItem collection to be displayed
   */
  protected abstract Collection<MenuItem> items();

  /**
   * Redraw the inventory clearing all items and redrawing them.
   */
  public void draw() {
    inventory.clear();
    itemMap = this.items().stream().collect(Collectors.toMap(MenuItem::getSlot, m -> m));
    itemMap.forEach((slot, item) -> this.getInventory().setItem(slot, item.getItemStack()));
  }

  /**
   * Open this menu to the player owner.
   *
   * @param redraw true if the inventory needs to be redrawed.
   */
  public void show(boolean redraw) {
    if (inventory == null) {
      inventory = Bukkit.createInventory(this, rows * 9, title);
      redraw = true;
    }
    if (redraw) {
      this.draw();
    }
    player.openInventory(this.getInventory());
  }

  /**
   * @return the player owner
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * @return the inventory title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return the number of rows
   */
  public int getRows() {
    return rows;
  }

  /**
   * @return all the items on this menu as a slot map
   */
  public Map<Integer, MenuItem> getItems() {
    return itemMap;
  }

  @Override
  public Inventory getInventory() {
    return inventory;
  }

  @Override
  public SinkPlugin getPlugin() {
    return plugin;
  }
}