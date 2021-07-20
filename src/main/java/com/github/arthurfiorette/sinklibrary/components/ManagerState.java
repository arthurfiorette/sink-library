package com.github.arthurfiorette.sinklibrary.components;

public enum ManagerState {
  ENABLING(false),
  ENABLED(true),
  DISABLING(true),
  DISABLED(false);

  private boolean enabled;

  ManagerState(final boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isEnabled() {
    return this.enabled;
  }
}
