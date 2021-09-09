package com.github.arthurfiorette.sinklibrary.component.loaders;

import com.github.arthurfiorette.sinklibrary.component.Component;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.NonNull;
import lombok.SneakyThrows;

@FunctionalInterface
public interface ComponentLoader {
  Component load();

  /**
   * @return an array of component loader that will call the constructor with
   * only one parameter: a BasePlugin one.
   */
  @SafeVarargs
  static ComponentLoader[] reflect(final BasePlugin plugin,
      final Class<? extends Component>... classes) {
    final ComponentLoader[] loader = new ComponentLoader[classes.length];

    for(int i = 0; i < classes.length; i++) {
      loader[i] = new ConstructorLoader(plugin, classes[i]);
    }

    return loader;
  }

  static ComponentLoader[] annotated(@NonNull final BasePlugin plugin) {
    return annotated(plugin, new Package[] { plugin.getClass().getPackage() });
  }

  @SuppressWarnings("unchecked")
  @SneakyThrows(IOException.class)
  static ComponentLoader[] annotated(@NonNull final BasePlugin plugin,
      @NonNull final Package[] packages) {
    final List<Class<? extends Component>> components = new ArrayList<>();
    final ClassLoader currentLoader = Thread.currentThread().getContextClassLoader();

    // Look trough all top level classes and filter who is in a package or a
    // subpackage that match one of the packages in the arguments, check if the
    // class is a Component and has the @Load annotation, collect all classes
    // that match all of these conditions and transform it in a component loader
    // array.
    for(final ClassInfo classInfo: ClassPath.from(currentLoader).getTopLevelClasses()) {
      if (Arrays.stream(packages).noneMatch(pkg -> classInfo.getName().startsWith(pkg.getName()))) {
        continue;
      }

      final Class<?> clazz = classInfo.load();

      if (!clazz.isAssignableFrom(Component.class)) {
        continue;
      }

      final LoadFrom loadAnnotation = clazz.getDeclaredAnnotation(LoadFrom.class);

      if (loadAnnotation == null) {
        continue;
      }

      final boolean sameClass = loadAnnotation.value().equals(plugin.getClass());

      if (!sameClass) {
        continue;
      }

      components.add((Class<? extends Component>) clazz);
    }

    return reflect(plugin, components.toArray(new Class[0]));
  }
}
