package com.github.arthurfiorette.sinklibrary.interfaces;

import com.github.arthurfiorette.sinklibrary.core.BaseModule;

/**
 * A class that implement this means that his instances need a BasePlugin to
 * work.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public interface BaseComponent {
  /**
   * @return his {@link BaseModule} owner
   */
  BaseModule getBasePlugin();
}
