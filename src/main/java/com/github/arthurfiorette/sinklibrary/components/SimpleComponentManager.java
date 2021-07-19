package com.github.arthurfiorette.sinklibrary.components;

import com.github.arthurfiorette.sinklibrary.exceptions.ComponentNotRegisteredException;
import com.github.arthurfiorette.sinklibrary.exceptions.IllegalComponentException;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import com.github.arthurfiorette.sinklibrary.interfaces.ComponentLoader;
import com.github.arthurfiorette.sinklibrary.interfaces.MultiComponent;
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
    this.plugin.log(Level.INFO, "Enabling services");

    this.updateComponents();

    for (final BaseService service : this.services.values()) {
      try {
        service.enable();
        this.plugin.log(Level.INFO, "Service §a%s§f enabled", service.getClass().getSimpleName());
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
    this.plugin.log(Level.WARNING, "Disabling all services");

    final BaseService[] servicesArr = this.services.values().toArray(new BaseService[0]);
    for (int i = servicesArr.length - 1; i >= 0; i--) {
      final BaseService service = servicesArr[i];
      try {
        service.disable();
        this.plugin.log(
            Level.WARNING,
            "Service §e%s§f disabled",
            service.getClass().getSimpleName()
          );
      } catch (final Exception e) {
        this.plugin.treatThrowable(
            service.getClass(),
            // Prevent infinite loop while disabling.
            new RuntimeException(e),
            "Could not disable this service"
          );
      }
    }

    this.plugin.log(Level.WARNING, "Services disabled");
    this.state = ManagerState.DISABLED;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends BaseComponent> T getComponent(final Class<T> clazz) {
    if (!this.state.isEnabled()) {
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

  private void updateComponents() {
    this.components.clear();
    this.services.clear();

    for (final ComponentLoader loader : this.plugin.components()) {
      final BaseComponent component = loader.get();
      
      if (component instanceof MultiComponent<?>) {
        this.registerMultiComponent(component);
        continue;
      }

      if (component instanceof BaseService) {
        this.registerAsService(component);
        continue;
      }

      this.registerAsComponent(component);
    }
  }

  private void registerMultiComponent(final BaseComponent component) {
    final MultiComponent<?> multiComponent = (MultiComponent<?>) component;
    final BaseComponent choosed = multiComponent.getComponent();

    if (choosed instanceof BaseService) {
      this.registerAsService(choosed);
      return;
    }

    this.registerAsComponent(choosed);
  }

  private void registerAsService(final BaseComponent component) {
    this.checkTypeParameters(component.getClass());
    final BaseService service = (BaseService) component;
    this.services.put(service.getClass(), service);
  }

  private void registerAsComponent(final BaseComponent component) {
    this.checkTypeParameters(component.getClass());
    this.components.put(component.getClass(), component);
  }
}
