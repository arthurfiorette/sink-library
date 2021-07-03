package com.github.arthurfiorette.sinklibrary.interfaces;

public interface BaseService extends BaseComponent {
  /**
   * Called when enabling this service
   * <p>
   * if the exception thrown ins't instaceof {@link RuntimeException}, the
   * plugin will be disabled.
   *
   * @throws Exception
   */
  void enable() throws Exception;

  /**
   * Called when disabling this service
   */
  void disable() throws Exception;
}
