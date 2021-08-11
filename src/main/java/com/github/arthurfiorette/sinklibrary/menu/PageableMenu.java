package com.github.arthurfiorette.sinklibrary.menu;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.menu.item.MenuItem;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class PageableMenu extends PrivateMenu {

  @Getter
  private int page = 1;

  protected List<MenuItem> lastPageableItems = new ArrayList<>();

  public PageableMenu(
    final BasePlugin plugin,
    final Player owner,
    final String title,
    final int rows
  ) {
    super(plugin, owner, title, rows);
  }

  public PageableMenu(final BasePlugin plugin, final Player owner, final Inventory inventory) {
    super(plugin, owner, inventory);
  }

  /**
   * This method MUST always return the same bytes.
   *
   * @return the pageable bytes
   */
  protected abstract byte[] pageableSlots();

  /**
   * This method is called every update request. There's no problem on returning
   * different values every time
   * <p>
   * You can use null values to use the default item.
   *
   * @return the pageable item list
   */
  protected abstract List<MenuItem> pageableItems();

  /**
   * {@inheritDoc}
   * <p>
   * Update static and pageable items
   */
  @Override
  public void update() {
    this.updatePageable();
    super.update();
  }

  public void updatePageable() {
    final byte[] slots = this.pageableSlots();

    this.lastPageableItems = this.pageableItems();

    // List first index to this page
    final int initial = slots.length * (this.page - 1);

    byte slotIndex = 0;
    // for from initial to last element.
    for (int i = initial; i < initial + slots.length; i++) {
      MenuItem item;
      try {
        item = this.lastPageableItems.get(i);
        // When the pageable list ends without completing all
        // pageableSlots().length size.
      } catch (final IndexOutOfBoundsException ignore) {
        item = null;
      }

      // The MenuItem ItemStack or an empty value.
      final ItemStack is = item != null ? item.getItem() : this.defaultItem;

      this.inventory.setItem(slots[slotIndex++], is);
    }
  }

  public void nextPage(final boolean update) {
    if (this.hasNextPage()) {
      this.page += 1;
    }
    if (update) {
      this.update();
    }
  }

  public void previousPage(final boolean update) {
    if (this.hasPreviousPage()) {
      this.page -= 1;
    }
    if (update) {
      this.update();
    }
  }

  /**
   * @return true if has a next page to be displayed
   */
  public boolean hasNextPage() {
    return this.page + 1 < this.lastPageableItems.size() / this.pageableSlots().length;
  }

  /**
   * @return true has a previous page to be displayed
   */
  public boolean hasPreviousPage() {
    return this.page > 0;
  }

  @Override
  public MenuItem getItemAt(final byte slot) {
    final MenuItem item = super.getItemAt(slot);
    final byte[] slots = this.pageableSlots();

    if (item != null) {
      return item;
    }

    int slotIndex = -1;
    for (int i = 0; i < slots.length; i++) {
      if (slot == slots[i]) {
        slotIndex = i;
        break;
      }
    }

    if (slotIndex == -1) {
      return null;
    }

    // List first index to this page
    final int initial = slots.length * (this.page - 1);

    return this.lastPageableItems.get(initial + slotIndex);
  }
}
