package com.github.arthurfiorette.sinklibrary.modules;

import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;

/**
 * @param <T> A common type that any possible registered component from this
 * class should implement.
 */
public interface MultiComponent<T extends BaseComponent> extends BaseComponent {

  Class<T> getCommonClass();

  <C extends T> C getComponent();
}
