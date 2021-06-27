package com.github.arthurfiorette.sinklibrary.menu;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.menu.item.MenuItem;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class PageableMenu extends PrivateMenu {

  private int page = 1;

  protected List<MenuItem> lastPageableItems;

  private ItemStack defaultItem = new ItemStack(Material.AIR);

  public PageableMenu(BasePlugin plugin, Player owner, String title, int rows) {
    super(plugin, owner, title, rows);
  }

  public PageableMenu(BasePlugin plugin, Player owner, Inventory inventory) {
    super(plugin, owner, inventory);
  }

  /**
   * This method must be static and always return the same bytes.
   */
  protected abstract byte[] pageableSlots();

  /**
   * This method is called every update request. There's no problem on returning
   * different values every time
   */
  protected abstract List<MenuItem> pageableItems();

  /**
   * {@inheritDoc}
   * <p>
   * Update static and pageable items
   */
  @Override
  public void update() {
    this.updateStatic();
    this.updatePageable();
  }

  public void updateStatic() {
    super.update();
  }

  public void updatePageable() {
    byte[] slots = pageableSlots();
    this.lastPageableItems = pageableItems();

    // List first index to this page
    int initial = slots.length * (page - 1);

    byte slotIndex = 0;
    // for from initial to last element.
    for(int i = initial; i < (initial + slots.length); i++) {
      MenuItem item;
      try {
        item = lastPageableItems.get(i);
      } catch (ArrayIndexOutOfBoundsException e) {
        item = null;
      }

      // The MenuItem ItemStack or an empty value.
      ItemStack is = item != null ? item.getItem() : defaultItem;

      inventory.setItem(slots[slotIndex++], is);
    }
  }

  public void nextPage(boolean update) {
    if (hasNextPage()) {
      this.page++;
      if (update) update();
    }
  }

  public void previousPage(boolean update) {
    if (hasPreviousPage()) {
      this.page--;
      if (update) update();
    }
  }

  /**
   * @return true if has a next page to be displayed
   */
  public boolean hasNextPage() {
    return this.page + 1 < this.lastPageableItems.size() / pageableSlots().length;
  }

  /**
   * @return true has a previous page to be displayed
   */
  public boolean hasPreviousPage() {
    return this.page > 0;
  }

  @Override
  public MenuItem getItemAt(byte slot) {
    MenuItem item = super.getItemAt(slot);
    byte[] slots = pageableSlots();

    if (item != null) {
      return item;
    }

    int slotIndex = -1;
    for(int i = 0; i < slots.length; i++) {
      if (slot == slots[i]) {
        slotIndex = i;
        break;
      }
    }

    if (slotIndex == -1) {
      return null;
    }

    // List first index to this page
    int initial = slots.length * (page - 1);

    return lastPageableItems.get(initial + slotIndex);
  }

  /**
   * Sets the item that will be placed when a page does not have enough items.
   */
  public void setDefaultItem(ItemStack emptySlot) {
    this.defaultItem = emptySlot;
  }
}
