package com.github.arthurfiorette.sinklibrary.core;

public enum ManagerState {
  ENABLING(true),
  ENABLED(true),
  DISABLING(false),
  DISABLED(false);

  private boolean enabled;

  ManagerState(final boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isEnabled() {
    return this.enabled;
  }
}
