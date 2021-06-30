package com.github.arthurfiorette.sinklibrary.core;

import com.github.arthurfiorette.sinklibrary.SinkPlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import com.google.common.collect.Iterables;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A simple component manager class that turns its services on and off in
 * first-in, last-out order.
 */
public final class SimpleComponentManager implements ComponentManager {

  private final Map<Class<? extends BaseComponent>, BaseComponent> components = new LinkedHashMap<>();
  private final Map<Class<? extends BaseService>, BaseService> services = new LinkedHashMap<>();
  private final SinkPlugin plugin;
  private ManagerState state = ManagerState.DISABLED;

  public SimpleComponentManager(final SinkPlugin plugin) {
    this.plugin = plugin;
    this.services.put(plugin.getClass(), plugin);
  }

  public void register(final BaseService[] services, final BaseComponent[] components) {
    for (final BaseComponent component : components) {
      this.components.put(component.getClass(), component);
    }
    for (final BaseService service : services) {
      this.services.put(service.getClass(), service);
    }
  }

  @Override
  public void enableServices() throws IllegalStateException {
    if (this.state.isEnabled()) {
      throw new IllegalStateException("Manager already started");
    }

    this.state = ManagerState.ENABLING;

    for (final BaseService service : this.services.values()) {
      try {
        service.enable();
      } catch (final Exception e) {
        this.plugin.treatThrowable(
            service.getClass(),
            e,
            "Throwable catch while enabling this service"
          );
      }
    }

    this.state = ManagerState.ENABLED;
  }

  @Override
  public void disableServices() throws IllegalStateException {
    if (!this.state.isEnabled()) {
      throw new IllegalStateException("Manager wasn't started");
    }

    this.state = ManagerState.DISABLING;

    final BaseService[] servicesArr = Iterables.toArray(this.services.values(), BaseService.class);
    for (int i = servicesArr.length - 1; i >= 0; i--) {
      final BaseService service = servicesArr[i];
      try {
        service.disable();
      } catch (final Exception e) {
        this.plugin.treatThrowable(
            service.getClass(),
            e,
            "Throwable catch while disabling this service"
          );
      }
    }

    this.state = ManagerState.DISABLED;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends BaseComponent> T getComponent(final Class<T> clazz) {
    return (T) this.components.get(clazz);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends BaseService> T getService(final Class<T> clazz) {
    return (T) this.services.get(clazz);
  }

  @Override
  public ManagerState getState() {
    return this.state;
  }
}
