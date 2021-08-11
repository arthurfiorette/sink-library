package com.github.arthurfiorette.sinklibrary.component.providers;

import com.github.arthurfiorette.sinklibrary.component.Component;
import com.github.arthurfiorette.sinklibrary.exception.sink.ComponentNotFoundException;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public interface ComponentProvider {

  void enableAll();

  void disableAll();

  <C extends Component> C get(@NonNull Class<C> clazz) throws ComponentNotFoundException;

  ComponentProvider.State state();

  @RequiredArgsConstructor
  public enum State {

    ENABLING(false),
    ENABLED(true),
    DISABLING(true),
    DISABLED(false);

    @Getter
    private final boolean enabled;
  }
}
