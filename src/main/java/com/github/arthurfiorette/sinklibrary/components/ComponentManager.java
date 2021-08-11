package com.github.arthurfiorette.sinklibrary.components;

import com.github.arthurfiorette.sinklibrary.exception.sink.ComponentNotRegisteredException;
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

  <T extends BaseComponent> T getComponent(Class<T> clazz) throws ComponentNotRegisteredException;

  ManagerState getState();
}
