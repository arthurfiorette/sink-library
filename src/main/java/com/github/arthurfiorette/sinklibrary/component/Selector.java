package com.github.arthurfiorette.sinklibrary.component;

/**
 * A selector is a class that can register different implementations of services
 * or components.
 * <p>
 * This can be used, for example, to register a different class for each version
 * to handle specific NMS imports and etc.
 *
 * @param <M> A common type that any possible registered component from this
 * class should implement.
 */
public interface Selector<M extends Component> {
  Class<M> getCommonClass();

  M select();
}
