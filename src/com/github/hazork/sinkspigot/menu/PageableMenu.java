package com.github.hazork.sinkspigot.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;

import com.github.hazork.sinkspigot.menu.item.MenuItem;
import com.google.common.collect.Lists;

public abstract class PageableMenu<T> extends SinkMenu {

    private List<List<MenuItem>> pageList = new ArrayList<>();
    protected int page = 0;

    public PageableMenu(Player player, String title, int rows) {
	super(player, title, rows);
    }

    protected abstract int[] pageableSlots();

    protected abstract List<T> requestValues();

    protected abstract MenuItem toItem(T object);

    @Override
    public void draw() {
	super.draw();
	pageList = Lists.partition(requestValues().stream().map(this::toItem).collect(Collectors.toList()),
		pageableSlots().length);
	List<MenuItem> items = pageList.get(page);
	items.forEach(i -> itemMap.put(i.getSlot(), i));
	ListIterator<MenuItem> iterator = items.listIterator();
	for(int i: pageableSlots()) {
	    if (iterator.hasNext()) {
		getInventory().setItem(i, iterator.next().getItemStack());
	    } else {
		break;
	    }
	}
    }

    public void nextPage() {
	if (hasNextPage()) {
	    page++;
	    draw();
	}
    }

    protected boolean hasNextPage() {
	return (page + 1) < getPageList().size();
    }

    public void previousPage() {
	if (hasPreviousPage()) {
	    page--;
	    draw();
	}
    }

    protected boolean hasPreviousPage() {
	return page > 0;
    }

    protected List<List<MenuItem>> getPageList() {
	return pageList;
    }

}