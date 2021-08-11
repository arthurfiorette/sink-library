package com.github.arthurfiorette.sinklibrary.component.loaders;

import com.github.arthurfiorette.sinklibrary.component.Component;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;

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

}
