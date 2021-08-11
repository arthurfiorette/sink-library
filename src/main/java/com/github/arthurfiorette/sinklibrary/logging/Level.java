package com.github.arthurfiorette.sinklibrary.logging;

import org.bukkit.ChatColor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * Modified to support Bukkit chat colors and more.
 * <p>
 * Levels used for identifying the severity of an event. Levels are organized
 * from most specific to least:
 * <ul>
 * <li>{@link #OFF} (most specific)</li>
 * <li>{@link #FATAL}</li>
 * <li>{@link #ERROR}</li>
 * <li>{@link #WARN}</li>
 * <li>{@link #INFO}</li>
 * <li>{@link #DEBUG}</li>
 * <li>{@link #TRACE}</li>
 * <li>{@link #ALL} (least specific)</li>
 * </ul>
 * Typically, configuring a level in a filter or on a logger will cause logging
 * events of that level and those that are more specific to pass through the
 * filter. A special level, {@link #ALL}, is guaranteed to capture all levels
 * when used in logging configurations.
 *
 * @see org.apache.logging.log4j.Level
 */
@AllArgsConstructor
public enum Level {
  /**
   * No events will be logged.
   */
  OFF(ChatColor.GRAY, "Off", 1),

  /**
   * A severe error that will prevent the application from continuing.
   */
  FATAL(ChatColor.DARK_RED, "Fatal", 100),

  /**
   * An error in the application, possibly recoverable.
   */
  ERROR(ChatColor.RED, "Error", 200),

  /**
   * An event that might possible lead to an error.
   */
  WARN(ChatColor.YELLOW, "Debug", 300),

  /**
   * An event for informational purposes.
   */
  INFO(ChatColor.AQUA, "Info", 400),

  /**
   * A general debugging event.
   */
  DEBUG(ChatColor.LIGHT_PURPLE, "Debug", 500),

  /**
   * A fine-grained debug message, typically capturing the flow through the
   * application.
   */
  TRACE(ChatColor.DARK_GREEN, "Trace", 600),

  /**
   * All events should be logged.
   */
  ALL(ChatColor.WHITE, "All", Integer.MAX_VALUE);

  @Getter
  @NonNull
  private ChatColor color;

  @Getter
  @NonNull
  private String name;

  private int intLevel;

  /**
   * Converts the string passed as argument to a level. If the conversion fails,
   * then this method returns {@link #DEBUG}.
   *
   * @param sArg The name of the desired Level.
   *
   * @return The Level associated with the String.
   */
  public static Level toLevel(final String sArg) {
    return Level.toLevel(sArg, DEBUG);
  }

  /**
   * Converts the string passed as argument to a level. If the conversion fails,
   * then this method returns the value of <code>defaultLevel</code>.
   *
   * @param name The name of the desired Level.
   * @param defaultLevel The Level to use if the String is invalid.
   *
   * @return The Level associated with the String.
   */
  public static Level toLevel(final String name, final Level defaultLevel) {
    if (name == null) {
      return defaultLevel;
    }

    for (final Level level : Level.values()) {
      if (level.name().equalsIgnoreCase(name)) {
        return level;
      }
    }

    return defaultLevel;
  }

  /**
   * Compares this level against the level passed as an argument and returns
   * true if this level is the same or more specific.
   *
   * @param level The level to check.
   *
   * @return True if the passed Level is more specific or the same as this
   * Level.
   */
  public boolean isAtLeastAsSpecificAs(@NonNull final Level level) {
    return this.intLevel <= level.intLevel;
  }

  /**
   * Compares this level against the level passed as an argument and returns
   * true if this level is the same or more specific.
   *
   * @param level The level to check.
   *
   * @return True if the passed Level is more specific or the same as this
   * Level.
   */
  public boolean isAtLeastAsSpecificAs(final int level) {
    return this.intLevel <= level;
  }

  /**
   * Compares the specified Level against this one.
   *
   * @param level The level to check.
   *
   * @return True if the passed Level is more specific or the same as this
   * Level.
   */
  public boolean lessOrEqual(@NonNull final Level level) {
    return this.intLevel <= level.intLevel;
  }

  /**
   * Compares the specified Level against this one.
   *
   * @param level The level to check.
   *
   * @return True if the passed Level is more specific or the same as this
   * Level.
   */
  public boolean lessOrEqual(final int level) {
    return this.intLevel <= level;
  }

  /**
   * Returns the integer value of the Level.
   *
   * @return the integer value of the Level.
   */
  public int intValue() {
    return this.intLevel;
  }

  public String toUpperCase() {
    return this.name.toUpperCase();
  }

  public String toLowerCase() {
    return this.name.toLowerCase();
  }

  public String colorized() {
    return this.color + this.name;
  }

  public String toColorUpperCase() {
    return this.colorized().toUpperCase();
  }

  public String toColorLowerCase() {
    return this.colorized().toLowerCase();
  }
}
