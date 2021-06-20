package com.github.arthurfiorette.sinklibrary.interfaces;

import com.github.arthurfiorette.sinklibrary.BasePlugin;

/**
 * A class that implement this means that his instances need a BasePlugin to
 * work.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public interface BaseComponent {

  /**
   * @return his plugin instance
   */
  BasePlugin getPlugin();

  default <T extends BasePlugin> T getPlugin(Class<T> clazz) {
    return clazz.cast(this.getPlugin());
  }
}
