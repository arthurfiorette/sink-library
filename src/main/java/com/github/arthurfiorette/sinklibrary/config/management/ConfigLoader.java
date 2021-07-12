package com.github.arthurfiorette.sinklibrary.config.management;

import com.github.arthurfiorette.sinklibrary.config.BaseConfig;
import com.github.arthurfiorette.sinklibrary.interfaces.BasePlugin;

public interface ConfigLoader {
  BaseConfig load(BasePlugin plugin);
}
