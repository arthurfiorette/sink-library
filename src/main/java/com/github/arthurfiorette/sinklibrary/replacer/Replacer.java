package com.github.arthurfiorette.sinklibrary.replacer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import org.bukkit.ChatColor;

/**
 * A better text replacer that supports PlaceholderAPI (if enabled)
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public class Replacer {

  private final Map<String, Supplier<String>> placeholders = new HashMap<>();

  public Replacer merge(final Replacer other) {
    placeholders.putAll(other.placeholders);
    return this;
  }

  /**
   * Add a placeholder to replace when called.
   *
   * @param placeholder the placeholder to be replaced
   * @param value the value to replace
   *
   * @return the instance
   */
  public Replacer add(final String placeholder, final String value) {
    return this.add(placeholder, () -> value);
  }

  /**
   * Add a placeholder to replace when called.
   *
   * @param placeholder the placeholder to be replaced
   * @param supplier the value to replace and only generated when called
   *
   * @return the instance
   */
  public Replacer add(final String placeholder, final Supplier<String> supplier) {
    placeholders.put(placeholder, supplier);
    return this;
  }

  /**
   * Replace the text with this atual set of placeholders.
   *
   * @param str the raw text
   *
   * @return the replaced text
   */
  public String replace(final String str) {
    String replaced = str;
    for (final Entry<String, Supplier<String>> entry : placeholders.entrySet()) {
      replaced = replaced.replace(entry.getKey(), entry.getValue().get());
    }
    return ChatColor.translateAlternateColorCodes('&', replaced);
  }

  /**
   * A static and compacted method that add the placeholder and replace.
   *
   * @param str the raw text
   * @param replacer a function to be added the placeholders in a other way
   *
   * @return the replaced text
   */
  public static String replace(final String str, final ReplacerFunction replacer) {
    return replacer.apply(new Replacer()).replace(str);
  }
}
