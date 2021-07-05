package com.github.arthurfiorette.sinklibrary.core;

import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;

public interface ComponentManager {
  /**
   * @throws IllegalStateException if this manager isn't disabled
   */
  void enableServices() throws IllegalStateException;

  /**
   * @throws IllegalStateException if this manager isn't enabled
   */
  void disableServices() throws IllegalStateException;

  <T extends BaseComponent> T getComponent(Class<T> clazz);

  ManagerState getState();
}
