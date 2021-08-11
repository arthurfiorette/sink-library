package com.github.arthurfiorette.sinklibrary.component.loaders;

import com.github.arthurfiorette.sinklibrary.component.Component;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.exception.sink.IllegalConstructorException;
import java.lang.reflect.Constructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConstructorLoader implements ComponentLoader {

  @Getter
  @NonNull
  private final BasePlugin plugin;

  @Getter
  @NonNull
  private final Class<? extends Component> clazz;

  @Override
  public Component load() {
    try {
      final Constructor<? extends Component> constructor =
        this.clazz.getConstructor(BasePlugin.class);
      constructor.setAccessible(true);
      return constructor.newInstance(this.plugin);
    } catch (final Exception e) {
      throw new IllegalConstructorException(this.clazz);
    }
  }
}
