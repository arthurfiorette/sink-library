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
import com.github.arthurfiorette.sinklibrary.logging.BaseLogger;

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
    checkState(this.state != State.DISABLED);

    // Check if it is a Service
    if (Service.class.isAssignableFrom(clazz)) {
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
    checkState(state == State.DISABLED);
    final BaseLogger logger = plugin.getBaseLogger();

    this.state = State.ENABLING;
    this.reloadComponents();

    logger.debug("Enabling services");

    for(final Service service: this.services.values()) {
      try {
        service.enable();
        logger.debug("Service §a%s§f enabled", service.getClass().getSimpleName());
      } catch (final Throwable e) {
        this.plugin.getExceptionHandler().handle(service.getClass(), e,
            "Could not enable this service.");
      }
    }

    logger.debug("Services enabled");
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
    checkState(state == State.ENABLED);
    final BaseLogger logger = plugin.getBaseLogger();

    this.state = State.DISABLING;
    logger.debug("Disabling all services");

    final Service[] servicesArr = this.services.values().toArray(new Service[0]);

    for(int i = servicesArr.length - 1; i >= 0; i--) {
      final Service service = servicesArr[i];

      try {
        service.disable();
        logger.debug("Service §e%s§f disabled", service.getClass().getSimpleName());
      } catch (final Throwable e) {
        this.plugin.getExceptionHandler().handle(service.getClass(), new RuntimeException(e),
            "Could not disable this service");
      }
    }

    logger.debug("Services disabled");
    this.state = State.DISABLED;
  }

  private void reloadComponents() {
    this.services.clear();
    this.components.clear();

    for(final ComponentLoader loader: this.plugin.components()) {
      Component component = loader.load();
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
