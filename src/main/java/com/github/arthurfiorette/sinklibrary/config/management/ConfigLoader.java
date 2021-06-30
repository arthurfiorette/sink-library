package com.github.arthurfiorette.sinklibrary.config.management;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.config.BaseConfig;

public interface ConfigLoader {
  BaseConfig load(BasePlugin plugin);
}
