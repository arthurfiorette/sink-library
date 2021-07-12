package com.github.arthurfiorette.sinklibrary.components;

import com.github.arthurfiorette.sinklibrary.exceptions.ComponentNotRegisteredException;
import com.github.arthurfiorette.sinklibrary.exceptions.IllegalComponentException;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import lombok.Getter;
import lombok.NonNull;

/**
 * A simple component manager class that turns its services on and off in
 * first-in, last-out order.
 * <p>
 * This class save this last component array, so you can use
 * {@link SinkPlugin#components()} programatically to load different component
 * and services.
 */
public class SimpleComponentManager implements ComponentManager {

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

    this.updateComponents();

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
    if (this.state != ManagerState.ENABLED) {
      throw new IllegalStateException("Manager isn't enabled");
    }

    this.state = ManagerState.DISABLING;
    this.plugin.log(Level.INFO, "Disabling all services");

    final BaseService[] servicesArr = this.services.values().toArray(new BaseService[0]);
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
    if (this.state != ManagerState.ENABLED) {
      throw new ComponentNotRegisteredException(clazz);
    }

    BaseComponent component = this.components.get(clazz);
    if (component == null) {
      component = this.services.get(clazz);
    }

    if (component == null) {
      throw new ComponentNotRegisteredException(clazz);
    }
    return (T) component;
  }

  public void updateComponents() {
    if (this.state != ManagerState.DISABLED) {
      throw new IllegalStateException("Manager can only update components when it is disabled.");
    }

    this.components.clear();
    this.services.clear();

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
}
