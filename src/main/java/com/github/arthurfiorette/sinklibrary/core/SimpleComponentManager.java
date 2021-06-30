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

  public SimpleComponentManager(SinkPlugin plugin) {
    this.plugin = plugin;
    this.services.put(plugin.getClass(), plugin);
  }

  public void register(BaseService[] services, BaseComponent[] components) {
    for (BaseComponent component : components) {
      this.components.put(component.getClass(), component);
    }
    for (BaseService service : services) {
      this.services.put(service.getClass(), service);
    }
  }

  @Override
  public void enableServices() throws IllegalStateException {
    if (state.isEnabled()) {
      throw new IllegalStateException("Manager already started");
    }

    this.state = ManagerState.ENABLING;

    for (BaseService service : services.values()) {
      try {
        service.enable();
      } catch (Exception e) {
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
    if (!state.isEnabled()) {
      throw new IllegalStateException("Manager wasn't started");
    }

    this.state = ManagerState.DISABLING;

    BaseService[] servicesArr = Iterables.toArray(this.services.values(), BaseService.class);
    for (int i = servicesArr.length - 1; i >= 0; i--) {
      BaseService service = servicesArr[i];
      try {
        service.disable();
      } catch (Exception e) {
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
  public <T extends BaseComponent> T getComponent(Class<T> clazz) {
    return (T) components.get(clazz);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends BaseService> T getService(Class<T> clazz) {
    return (T) services.get(clazz);
  }

  @Override
  public ManagerState getState() {
    return this.state;
  }
}
