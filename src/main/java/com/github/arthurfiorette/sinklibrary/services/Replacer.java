package com.github.arthurfiorette.sinklibrary.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;

/**
 * A better text replacer that supports PlaceholderAPI (if enabled)
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public class Replacer {

  private Map<String, Supplier<String>> placeholders = new HashMap<>();

  /**
   * Add a placeholder to replace when called.
   *
   * @param placeholder the placeholder to be replaced
   * @param value the value to replace
   *
   * @return the instance
   */
  public Replacer add(String placeholder, String value) {
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
  public Replacer add(String placeholder, Supplier<String> supplier) {
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
  public String replace(String str) {
    String replaced = str;
    for (Entry<String, Supplier<String>> entry : placeholders.entrySet()) {
      replaced = replaced.replace(entry.getKey(), entry.getValue().get());
    }
    return SpigotService.setColors(replaced);
  }

  /**
   * Replace the text with this atual set of placeholders. And use
   * PlaceholderApi if loaded
   *
   * @param str the raw text
   * @param player the player
   *
   * @return the replaced text
   */
  public String replace(String str, OfflinePlayer player) {
    String text = str;
    if (canUsePlaceholderAPI()) {
      text = PlaceholderAPI.setPlaceholders(player, this.replace(str));
    }
    return this.replace(text);
  }

  /**
   * A static and compacted method that add the placeholder and replace.
   *
   * @param str the raw text
   * @param replacer a function to be added the placeholders in a other way
   *
   * @return the replaced text
   */
  public static String replace(String str, UnaryOperator<Replacer> replacer) {
    return replacer.apply(new Replacer()).replace(str);
  }

  /**
   * A static and compacted method that add the placeholder and replace. And use
   * PlaceholderApi if loaded
   *
   * @param str the raw text
   * @param player the player
   * @param replacer a function to be added the placeholders in a other way
   *
   * @return the replaced text
   */
  public static String replace(String str, OfflinePlayer player, UnaryOperator<Replacer> replacer) {
    return replacer.apply(new Replacer()).replace(str, player);
  }

  /**
   * Checks whether PlaceholderAPI is enabled. This can be used to find out if
   * you will use PAPI to replace.
   *
   * @return true if it can
   */
  public static boolean canUsePlaceholderAPI() {
    return SpigotService.hasPlugin("PlaceholderAPI");
  }
}
