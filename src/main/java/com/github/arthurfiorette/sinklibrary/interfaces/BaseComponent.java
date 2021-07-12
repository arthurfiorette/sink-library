package com.github.arthurfiorette.sinklibrary.interfaces;

/**
 * A class that implement this means that his instances need a BasePlugin to
 * work.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public interface BaseComponent {
  /**
   * @return his {@link BasePlugin} owner
   */
  BasePlugin getBasePlugin();

  /**
   * This method allows you to change the assigned class from the component
   * manager, allowing you to use an interface or something similar to assign
   * it, you can use different services like nms classes, and just request an
   * interface to the component manager.
   * <p>
   * With this example, you can enable a different NmsX class per version, and
   * only get it using componentManager.getComponent(Nms.class).
   * <p>
   *
   * <pre>
   * interface Nms { ... }
   *
   * // getRegistrationClass() returns Nms.class;
   * class NmsA implements BaseComponent, Nms { ... }
   *
   * // getRegistrationClass() returns Nms.class;
   * class NmsB implements BaseComponent, Nms { ... }
   * </pre>
   *
   * @return this registration class
   */
  default Class<? extends BaseComponent> getRegistrationClass() {
    return getClass();
  }
}
