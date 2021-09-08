package com.github.arthurfiorette.sinklibrary.events;

import com.github.arthurfiorette.sinklibrary.component.Service;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * A class that joins bukkit listener with sink service system. To listen for
 * any event, create a method annotated with {@link EventHandler}. You don't
 * have to register this listener manually.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public interface SinkListener extends Listener, Service {
  @Override
  default void enable() {
    Bukkit.getPluginManager().registerEvents(this, getBasePlugin());
  }

  @Override
  default void disable() {
    HandlerList.unregisterAll(this);
  }
}
