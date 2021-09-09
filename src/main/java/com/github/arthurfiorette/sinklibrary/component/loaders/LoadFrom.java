package com.github.arthurfiorette.sinklibrary.component.loaders;

import com.github.arthurfiorette.sinklibrary.core.SinkPlugin;

public @interface LoadFrom {

  /**
   * Must match the same class of his owner.
   */
  Class<? extends SinkPlugin> value();

}
