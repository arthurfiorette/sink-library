package com.github.arthurfiorette.sinklibrary.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.menu.item.MenuItem;
import com.google.common.collect.Lists;

/**
 * An minecraft menu with better methods and a fanciest way to handle with. But
 * with pages, that can be paginated with previousPage() and nextPage()
 *
 * @param <T> the item type to be pageable
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public abstract class PageableMenu<T> extends SinkMenu {

  private List<List<MenuItem>> pageList = new ArrayList<>();
  /**
   * the page number being displayed now.
   */
  protected int page = 0;

  /**
   * Constructs a new PageableMenu.
   *
   * @param plugin the sink plugin instance
   * @param player the player owner;
   * @param title the inventory title
   * @param rows the number of inventory rows
   */
  protected PageableMenu(BasePlugin plugin, Player player, String title, int rows) {
    super(plugin, player, title, rows);
  }

  /**
   * @return the pageable slots
   */
  protected abstract int[] pageableSlots();

  /**
   * @return all the objects to be paginated
   */
  protected abstract Collection<T> requestValues();

  /**
   * Transform the object to a MenuItem to be displayed.
   *
   * @param object the object to be transformed
   *
   * @return the MenuItem from this object
   */
  protected abstract MenuItem toItem(T object);

  @Override
  public void draw() {
    super.draw();
    pageList = Lists.partition(this.requestValues().stream().map(this::toItem).collect(Collectors.toList()),
        this.pageableSlots().length);
    List<MenuItem> items = pageList.get(page);
    items.forEach(i -> itemMap.put(i.getSlot(), i));
    ListIterator<MenuItem> iterator = items.listIterator();
    for(int i: this.pageableSlots()) {
      if (!iterator.hasNext()) {
        break;
      }

      this.getInventory().setItem(i, iterator.next().getItemStack());
    }
  }

  /**
   * Try to advance to the next page. to know if it can go to the next page,
   * use: {@link PageableMenu#hasNextPage()}.
   */
  public void nextPage() {
    if (this.hasNextPage()) {
      page++;
      this.draw();
    }
  }

  /**
   * @return true if has a next page to be displayed
   */
  protected boolean hasNextPage() {
    return (page + 1) < this.getPageList().size();
  }

  /**
   * Try to advance to the previous page. to know if it can go to the previous
   * page, use: {@link PageableMenu#hasPreviousPage()}.
   */
  public void previousPage() {
    if (this.hasPreviousPage()) {
      page--;
      this.draw();
    }
  }

  /**
   * @return true has a previous page to be displayed
   */
  protected boolean hasPreviousPage() {
    return page > 0;
  }

  /**
   * @return a list with a list of menu items. each list is a page.
   */
  protected List<List<MenuItem>> getPageList() {
    return pageList;
  }
}
