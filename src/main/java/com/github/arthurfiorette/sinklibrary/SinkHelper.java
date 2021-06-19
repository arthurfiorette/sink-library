package com.github.arthurfiorette.sinklibrary;

import com.github.arthurfiorette.sinklibrary.config.YmlHelper;
import com.github.arthurfiorette.sinklibrary.scheduler.SinkScheduler;
import java.util.logging.Level;

/**
 * An interface to deliver many methods from this library
 *
 * @author https://github.com/Hazork/sink-library/
 */
public interface SinkHelper extends SinkScheduler, YmlHelper {
  /**
   * Log any information with the plugin tag and etc. Best replacement for
   * System.out.println()
   *
   * @param level the log level.
   * @param msg any information that needs to be logged
   * @param args the arguments to replace the message.
   * {@link String#format(String, Object...)}
   */
  default void log(Level level, String msg, Object... args) {
    this.getPlugin().log(level, msg, args);
  }

  /**
   * Handles exceptions in a better visual way
   *
   * @param author the class author
   * @param exc the exception
   * @param message the message
   * @param args the arguments to replace the message.
   * {@link String#format(String, Object...)}
   */
  default void treatException(Class<?> author, Exception exc, String message, Object... args) {
    this.getPlugin().treatException(author, exc, message, args);
  }
}
