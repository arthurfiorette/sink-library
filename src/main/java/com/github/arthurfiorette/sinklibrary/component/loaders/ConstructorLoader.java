package com.github.arthurfiorette.sinklibrary.component.loaders;

import com.github.arthurfiorette.sinklibrary.component.Component;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.exception.sink.IllegalConstructorException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class ConstructorLoader implements ComponentLoader {

  @Getter
  @NonNull
  private final BasePlugin plugin;

  @Getter
  @NonNull
  private final Class<? extends Component> clazz;

  @Override
  @SneakyThrows
  public Component load() {
    if (isNotInstantiable()) {
      throw new IllegalConstructorException(clazz);
    }

    final Constructor<?> constructor = findConstructor();

    if (constructor == null) {
      throw new IllegalConstructorException(clazz);
    }

    constructor.setAccessible(true);

    if (constructor.getParameterCount() == 0) {
      return (Component) constructor.newInstance();
    }

    return (Component) constructor.newInstance(this.plugin);
  }

  @SneakyThrows
  private Constructor<?> findConstructor() {
    if (clazz.getConstructor() != null) {
      return clazz.getConstructor();
    }

    return Arrays
      .stream(clazz.getConstructors())
      .filter(
        c -> {
          return (
            c.getParameterCount() == 0 ||
            c.getParameterCount() == 1 &&
            BasePlugin.class.isAssignableFrom(c.getParameterTypes()[0])
          );
        }
      )
      .findFirst()
      .orElse(null);
  }

  private boolean isNotInstantiable() {
    return (
      clazz == null ||
      clazz.isPrimitive() ||
      Modifier.isAbstract(clazz.getModifiers()) ||
      clazz.isInterface() ||
      clazz.isArray()
    );
  }
}
