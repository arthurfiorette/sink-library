package com.github.arthurfiorette.sinklibrary.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;

public interface BaseConfig extends BaseComponent {

  /**
   * Reload his configuration
   */
  void reload();

  /**
   * @return the file configuration from this yml instance
   */
  FileConfiguration getConfig();

  /**
   * @return this yml file as a File object
   */
  File getFile();

}
