package com.github.arthurfiorette.sinklibrary.component.providers;

import com.github.arthurfiorette.sinklibrary.component.Component;
import com.github.arthurfiorette.sinklibrary.exception.sink.ComponentNotFoundException;

import lombok.NonNull;

public interface ComponentProvider {
  void enableAll();

  void disableAll();

  <C extends Component> C get(@NonNull Class<C> clazz) throws ComponentNotFoundException;

  ComponentProvider.State state();

  public enum State {
    ENABLING,
    ENABLED,
    DISABLING,
    DISABLED;
  }
}
