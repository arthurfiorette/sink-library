package com.github.arthurfiorette.sinklibrary.core;

import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;

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

  <T extends BaseService> T getService(Class<T> clazz);

  ManagerState getState();

}
