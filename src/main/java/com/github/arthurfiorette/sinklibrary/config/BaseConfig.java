package com.github.arthurfiorette.sinklibrary.config;

import com.github.arthurfiorette.sinklibrary.component.Component;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;

public interface BaseConfig extends Component {
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
