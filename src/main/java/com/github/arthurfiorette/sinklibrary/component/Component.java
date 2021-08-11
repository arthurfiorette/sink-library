package com.github.arthurfiorette.sinklibrary.component;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;

/**
 * A class that implement this means that his instances need a BasePlugin to
 * work.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public interface Component {
  
  /**
   * @return his {@link BasePlugin} owner
   */
  BasePlugin getBasePlugin();
}
