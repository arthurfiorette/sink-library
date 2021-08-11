package com.github.arthurfiorette.sinklibrary.components;

import com.github.arthurfiorette.sinklibrary.core.SinkPlugin;
import com.github.arthurfiorette.sinklibrary.exception.sink.ComponentNotRegisteredException;
import com.github.arthurfiorette.sinklibrary.exception.sink.IllegalComponentException;
import com.github.arthurfiorette.sinklibrary.interfaces.*;
import com.github.arthurfiorette.sinklibrary.logging.Level;
import java.util.LinkedHashMap;
import java.util.Map;
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
  }

  private void checkTypeParameters(final Class<? extends Object> clazz) {
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
    this.plugin.log(Level.INFO, this, "Enabling services");

    this.updateComponents();

    for (final BaseService service : this.services.values()) {
      try {
        service.enable();
        this.plugin.log(
            Level.DEBUG,
            this,
            "Service §a%s§f enabled",
            service.getClass().getSimpleName()
          );
      } catch (final Exception e) {
        this.plugin.getExceptionHandler().handle(service.getClass(), e, "Could not enable this service.");
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
    this.plugin.log(Level.INFO, this, "Disabling all services");

    final BaseService[] servicesArr = this.services.values().toArray(new BaseService[0]);
    for (int i = servicesArr.length - 1; i >= 0; i--) {
      final BaseService service = servicesArr[i];
      try {
        service.disable();
        this.plugin.log(
            Level.DEBUG,
            this,
            "Service §e%s§f disabled",
            service.getClass().getSimpleName()
          );
      } catch (final Exception e) {
        this.plugin.getExceptionHandler().handle(
            service.getClass(),
            // Prevent infinite loop while disabling.
            new RuntimeException(e),
            "Could not disable this service"
          );
      }
    }

    this.plugin.log(Level.INFO, this, "Services disabled");
    this.state = ManagerState.DISABLED;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends BaseComponent> T getComponent(final Class<T> clazz) {
    if (!this.state.isEnabled()) {
      throw new IllegalStateException("This manager is disabled");
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

  private void updateComponents() {
    this.components.clear();
    this.services.clear();

    for (final ComponentLoader loader : this.plugin.components()) {
      final BaseComponent component = loader.get();

      if (component instanceof MultiComponent<?>) {
        this.registerMultiComponent((MultiComponent<?>) component);
        continue;
      }

      if (component instanceof BaseService) {
        this.registerAsService((BaseService) component);
        continue;
      }

      this.registerAsComponent(component);
    }
  }

  @SuppressWarnings("unchecked")
  private void registerMultiComponent(final MultiComponent<?> multiComponent) {
    final BaseComponent component = multiComponent.getComponent();
    final Class<?> registrationClass = multiComponent.getRegistrationClass();

    this.checkTypeParameters(registrationClass);

    if (component instanceof BaseService) {
      this.services.put((Class<BaseService>) registrationClass, (BaseService) component);
      return;
    }

    this.components.put((Class<BaseService>) registrationClass, (BaseService) component);
  }

  private void registerAsService(final BaseService service) {
    this.checkTypeParameters(service.getClass());
    this.services.put(service.getClass(), service);
  }

  private void registerAsComponent(final BaseComponent component) {
    this.checkTypeParameters(component.getClass());
    this.components.put(component.getClass(), component);
  }
}
