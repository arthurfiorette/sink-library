package com.github.arthurfiorette.sinklibrary.logging;

import org.apache.logging.log4j.Level;

/**
 * Interface that must be implemented to allow custom event filtering. It is
 * highly recommended that applications make use of the Filters provided with
 * this implementation before creating their own. This interface supports
 * "global" filters (i.e. - all events must pass through them first), attached
 * to specific loggers and associated with Appenders. It is recommended that,
 * where possible, Filter implementations create a generic filtering method that
 * can be called from any of the filter methods.
 */
public interface LogFilter {
  /**
   * The result that can returned from a filter method call.
   */
  public enum Result {
    /**
     * The event will be processed without further filtering based on the log
     * Level.
     */
    ACCEPT,
    /**
     * No decision could be made, further filtering should occur.
     */
    NEUTRAL,
    /**
     * The event should not be processed.
     */
    DENY,
  }

  Result filter(BaseLogger logger, Level level, String message, Object... args);

  Result filter(BaseLogger logger, Level level, String message, Throwable throwable);

  Result filter(BaseLogger logger, Level level, Class<?> author, String message, Object... args);

  Result filter(
    BaseLogger logger,
    Level level,
    Class<?> author,
    String message,
    Throwable throwable
  );
}
