package com.github.arthurfiorette.sinklibrary.interfaces;

/**
 * A class that implement this means that his instances need a BasePlugin to
 * work.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public interface BaseComponent {
  /**
   * @return his {@link BasePlugin} owner
   */
  BasePlugin getBasePlugin();

}
