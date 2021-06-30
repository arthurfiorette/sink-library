package com.github.arthurfiorette.sinklibrary.interfaces;

public interface BaseService extends BaseComponent {
  /**
   * Called when enabling this service
   */
  void enable() throws Exception;

  /**
   * Called when disabling this service
   */
  void disable() throws Exception;
}
