package com.github.arthurfiorette.sinklibrary.interfaces;

public interface BaseService extends BaseComponent {
  /**
   * Called when enabling this service
   * <p>
   * if the thrown exception is not an instance of {@link RuntimeException}, the
   * plugin will be disabled.
   *
   * @throws Exception An exception thrown means the enable cannot end
   * successfully.
   */
  void enable() throws Exception;

  /**
   * Called when disabling this service
   * 
   * @throws Exception An exception thrown means the enable cannot end
   * successfully.
   */
  void disable() throws Exception;
}
