package com.github.arthurfiorette.sinklibrary.component.loaders;

import com.github.arthurfiorette.sinklibrary.component.Component;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.exception.sink.IllegalConstructorException;
import java.lang.reflect.Constructor;
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
    final Constructor<?> constructor = findConstructor();

    if (constructor == null) {
      throw new IllegalConstructorException(clazz);
    }

    constructor.setAccessible(true);
    return (Component) constructor.newInstance(this.plugin);
  }

  @SneakyThrows
  private Constructor<?> findConstructor() {
    return Arrays
      .stream(clazz.getConstructors())
      .filter(
        c -> {
          return (
            c.getParameterCount() == 1 &&
            BasePlugin.class.isAssignableFrom(c.getParameterTypes()[0])
          );
        }
      )
      .findFirst()
      .orElse(null);
  }
}
