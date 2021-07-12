package com.github.arthurfiorette.sinklibrary.core;

import com.github.arthurfiorette.sinklibrary.exceptions.ComponentNotRegisteredException;
import com.github.arthurfiorette.sinklibrary.exceptions.IllegalComponentException;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import com.google.common.collect.Iterables;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import lombok.Getter;
import lombok.NonNull;

/**
 * A simple component manager class that turns its services on and off in
 * first-in, last-out order.
 */
public final class SimpleComponentManager implements ComponentManager {

  private final Map<Class<? extends BaseComponent>, BaseComponent> components = new LinkedHashMap<>();

  private final Map<Class<? extends BaseService>, BaseService> services = new LinkedHashMap<>();

  @Getter
  @NonNull
  private final SinkPlugin plugin;

  @Getter
  private ManagerState state = ManagerState.DISABLED;

  public SimpleComponentManager(final SinkPlugin plugin) {
    this.plugin = plugin;
    this.services.put(plugin.getClass(), plugin);

    for (final BaseComponent component : plugin.components()) {
      final Class<? extends BaseComponent> clazz = component.getClass();
      this.checkTypeParameters(clazz);

      // Service
      if (component instanceof BaseService) {
        final BaseService service = (BaseService) component;
        this.services.put(service.getClass(), service);
        continue;
      }

      // Component
      this.components.put(clazz, component);
    }
  }

  public void checkTypeParameters(final Class<? extends Object> clazz) {
    if (clazz.getTypeParameters().length > 0) {
      throw new IllegalComponentException(clazz);
    }
  }

  @Override
  public void enableServices() throws IllegalStateException {
    if (this.state.isEnabled()) {
      throw new IllegalStateException("Manager already started");
    }

    this.state = ManagerState.ENABLING;
    this.plugin.log(Level.INFO, "Enabling all services");

    for (final BaseService service : this.services.values()) {
      try {
        service.enable();
        this.plugin.log(Level.INFO, "Service %s enabled", service.getClass().getSimpleName());
      } catch (final Exception e) {
        this.plugin.treatThrowable(service.getClass(), e, "Could not enable this service.");
      }
    }

    this.plugin.log(Level.INFO, "Services enabled");
    this.state = ManagerState.ENABLED;
  }

  @Override
  public void disableServices() throws IllegalStateException {
    if (!this.state.isEnabled()) {
      throw new IllegalStateException("Manager wasn't started");
    }

    this.state = ManagerState.DISABLING;
    this.plugin.log(Level.INFO, "Disabling all services");

    final BaseService[] servicesArr = Iterables.toArray(this.services.values(), BaseService.class);
    for (int i = servicesArr.length - 1; i >= 0; i--) {
      final BaseService service = servicesArr[i];
      try {
        service.disable();
        this.plugin.log(Level.INFO, "Service %s disabled", service.getClass().getSimpleName());
      } catch (final Exception e) {
        this.plugin.treatThrowable(
            service.getClass(),
            // Prevent infinite loop while disabling.
            new RuntimeException(e),
            "Could not disable this service"
          );
      }
    }

    this.plugin.log(Level.INFO, "Services disabled");
    this.state = ManagerState.DISABLED;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends BaseComponent> T getComponent(final Class<T> clazz) {
    BaseComponent component = this.components.get(clazz);
    if (component == null) {
      component = this.services.get(clazz);
    }

    if (component == null) {
      throw new ComponentNotRegisteredException(clazz);
    }
    return (T) component;
  }
}