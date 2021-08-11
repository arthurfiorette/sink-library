package com.github.arthurfiorette.sinklibrary.component.providers;

import static com.google.common.base.Preconditions.checkState;

import com.github.arthurfiorette.sinklibrary.component.Component;
import com.github.arthurfiorette.sinklibrary.component.Selector;
import com.github.arthurfiorette.sinklibrary.component.Service;
import com.github.arthurfiorette.sinklibrary.component.loaders.ComponentLoader;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.exception.SimpleExceptionHandler;
import com.github.arthurfiorette.sinklibrary.exception.sink.ComponentNotFoundException;
import com.github.arthurfiorette.sinklibrary.exception.sink.GenericComponentException;
import com.github.arthurfiorette.sinklibrary.logging.Level;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
public class SimpleComponentProvider implements ComponentProvider {

  @Getter
  @NonNull
  private final BasePlugin plugin;

  @Getter
  private final Map<Class<?>, Component> components = new HashMap<>();

  @Getter
  private final Map<Class<?>, Service> services = new HashMap<>();

  @Getter
  @Accessors(fluent = true)
  private State state = State.DISABLED;

  @Override
  @SuppressWarnings("unchecked")
  public <C extends Component> C get(@NonNull final Class<C> clazz)
    throws ComponentNotFoundException {
    Preconditions.checkState(this.state.isEnabled());

    // Check if it is a Service
    if (clazz.isAssignableFrom(Service.class)) {
      final Service service = this.services.get(clazz);

      if (service == null) {
        throw new ComponentNotFoundException(clazz);
      }

      return (C) service;
    }

    final Component component = this.components.get(clazz);

    if (component == null) {
      throw new ComponentNotFoundException(clazz);
    }

    return (C) component;
  }

  @Override
  @Synchronized("state")
  public void enableAll() {
    checkState(!state.isEnabled());

    this.state = State.ENABLING;
    this.reloadComponents();

    this.plugin.log(Level.DEBUG, this, "Enabling services");

    for (final Service service : this.services.values()) {
      try {
        service.enable();
        this.plugin.log(
            Level.DEBUG,
            this,
            "Service §a%s§f enabled",
            service.getClass().getSimpleName()
          );
      } catch (final Throwable e) {
        this.plugin.getExceptionHandler()
          .handle(service.getClass(), e, "Could not enable this service.");
      }
    }

    this.plugin.log(Level.DEBUG, "Services enabled");
    this.state = State.ENABLED;
  }

  /**
   * Any exception thrown while disabling any service will be wrapped into a
   * {@link RuntimeException} to prevent an infinite loop. See
   * {@link SimpleExceptionHandler#handle(Class, Throwable, String, Object...)}
   * <p>
   * {@inheritDoc}
   */
  @Override
  @Synchronized("state")
  public void disableAll() {
    checkState(state.isEnabled());

    this.state = State.DISABLING;
    this.plugin.log(Level.DEBUG, this, "Disabling all services");

    final Service[] servicesArr = this.services.values().toArray(new Service[0]);

    for (int i = servicesArr.length - 1; i >= 0; i--) {
      final Service service = servicesArr[i];

      try {
        service.disable();
        this.plugin.log(
            Level.DEBUG,
            this,
            "Service §e%s§f disabled",
            service.getClass().getSimpleName()
          );
      } catch (final Throwable e) {
        this.plugin.getExceptionHandler()
          .handle(service.getClass(), new RuntimeException(e), "Could not disable this service");
      }
    }

    this.plugin.log(Level.DEBUG, this, "Services disabled");
    this.state = State.DISABLED;
  }

  private void reloadComponents() {
    this.services.clear();
    this.components.clear();

    for (final ComponentLoader supplier : this.plugin.components()) {
      Component component = supplier.load();
      Class<?> clazz = component.getClass();

      // Load first to check if is a selector.
      if (component instanceof Selector) {
        final Selector<?> selector = (Selector<?>) component;

        component = selector.select();
        clazz = selector.getCommonClass();
      }

      // Check if this class is generic
      if (clazz.getTypeParameters().length > 0) {
        throw new GenericComponentException(clazz);
      }

      // Then register it
      if (component instanceof Service) {
        this.services.put(clazz, (Service) component);
      } else {
        this.components.put(clazz, component);
      }
    }
  }
}
