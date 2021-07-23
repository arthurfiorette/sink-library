package com.github.arthurfiorette.sinklibrary.config.management;

import com.github.arthurfiorette.sinklibrary.config.BaseConfig;
import com.github.arthurfiorette.sinklibrary.core.BaseModule;

public interface ConfigLoader {
  BaseConfig load(BaseModule plugin);
}
