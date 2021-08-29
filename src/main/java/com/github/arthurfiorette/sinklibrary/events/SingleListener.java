package com.github.arthurfiorette.sinklibrary.events;

import com.github.arthurfiorette.sinklibrary.component.Service;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.executor.TaskRunner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.PluginManager;

@AllArgsConstructor
@RequiredArgsConstructor
public abstract class SingleListener<E extends Event> implements Service, Listener, EventExecutor {

  @Getter
  @NonNull
  private final Class<E> eventClass;

  @Getter
  @NonNull
  private final BasePlugin basePlugin;

  @Getter
  @NonNull
  private final EventPriority eventPriority;

  @Getter
  private TaskRunner runner = null;

  protected abstract void handle(E event);

  @Override
  public void enable() throws Exception {
    final PluginManager pluginManager = basePlugin.getServer().getPluginManager();
    pluginManager.registerEvent(eventClass, this, eventPriority, this, basePlugin);
  }

  @Override
  public void disable() throws Exception {
    HandlerList.unregisterAll(this);
  }

  /**
   * @param listener not used, can be null
   * @param event the event. Must be instanceof E
   */
  @Override
  @SuppressWarnings("unchecked")
  public final void execute(final Listener listener, @NonNull final Event event) throws EventException {
    if (!eventClass.isAssignableFrom(event.getClass())) {
      // By default bukkit execution, this never gets here.
      // Only executing a different event can cause this error.
      throw new EventException("Unknown event or listener");
    }

    if (runner != null) {
      runner.run(basePlugin, () -> this.handle((E) event));
    } else {
      this.handle((E) event);
    }
  }
}
