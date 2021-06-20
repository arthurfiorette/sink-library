package com.github.arthurfiorette.sinklibrary.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;

/**
 * A service class that handles with texts.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public final class TextService {

  /**
   * A private constructor prevent callers from accidentally instantiating an
   * instance.
   */
  private TextService() {}

  /**
   * Returns a progress bar in String.
   * <p>
   * {@code buildProgressBar(10, #, *, 6, 10)} will return {@code######****}.
   * <p>
   * Same as {@link TextService#buildProgressBar(int, String, String, double)}.
   *
   * @param width the width of the progress bar
   * @param incomplete the text placed when your representative percentage is
   * incompleted
   * @param complete the text placed when your representative percentage is
   * completed
   * @param currentValue the progress value
   * @param maxValue the highest possible value
   *
   * @return the progress bar string
   *
   * @see TextService#buildProgressBar(int, String, String, double)
   */
  public static String buildProgressBar(
    int width,
    String incomplete,
    String complete,
    long currentValue,
    long maxValue
  ) {
    return buildProgressBar(width, incomplete, complete, (double) currentValue / maxValue);
  }

  /**
   * Returns a progress bar in string.
   * <p>
   * {@code buildProgressBar(10, #, *, 0.6)} will return {@code######****}.
   *
   * @param width the width of the progress bar
   * @param incomplete the text placed when your representative percentage is
   * incompleted
   * @param complete the text placed when your representative percentage is
   * completed
   * @param percent the percentage value to build the bar
   *
   * @return the progress bar string
   */
  public static String buildProgressBar(
    int width,
    String incomplete,
    String complete,
    double percent
  ) {
    if (percent > 1 || percent < 0) {
      throw new IllegalArgumentException("Percent should be between 1 and 0");
    }
    StringBuilder sb = new StringBuilder();
    double i = 0;
    for (; i < percent * width; i++) {
      sb.append(complete);
    }
    for (; i < width; i++) {
      sb.append(incomplete);
    }
    return sb.toString();
  }

  public static final String LINE_SEPARATOR =
    "a9f879156392911e229ffccd7618ed88213f293f472ca5eb5e79da1bb698dc45"; // S2

  /**
   * Split any minecraft colored text into sized lines without losing the color.
   *
   * @param text the raw text
   * @param width the width to split the lines
   *
   * @return the list with splitted lines
   */
  public static List<String> splitTextColored(String text, int width) {
    return splitText(forceColor(text), width, "");
  }

  /**
   * Split any text into sized lines.
   *
   * @param text the raw text
   * @param width the width to split the lines
   * @param newLine the text to be placed on every new line
   *
   * @return the list with splitted lines
   */
  public static List<String> splitText(String text, int width, String newLine) {
    return Arrays.asList(
      WordUtils.wrap(text, width, LINE_SEPARATOR + newLine, false).split(LINE_SEPARATOR)
    );
  }

  /**
   * Force a text to be colored placing the color in every word..
   * <p>
   * Ex: {@code '§6Hello World§f!'} will return {@code '§6Hello §6World§f!'}.
   *
   * @param text the raw text
   *
   * @return the forced text
   */
  public static String forceColor(String text) {
    List<String> texts = new ArrayList<>();
    for (String str : text.split("§")) {
      if (str.isEmpty()) {
        continue;
      }
      if (!str.startsWith("§")) {
        str = "§" + str;
      }
      Pattern isntColor = Pattern.compile("§[^0123456789AaBbCcDdEeFfKkLlMmNnOoRr]");
      if (isntColor.matcher(str).find()) {
        texts.add(str);
      } else {
        String lastColor = ChatColor.getLastColors(str);
        List<String> words = new ArrayList<>();
        for (String word : str.split(" ")) {
          if (!word.startsWith("§")) {
            word = lastColor + word;
          }
          words.add(word);
        }
        texts.add(String.join(" ", words));
      }
    }
    return String.join("", texts);
  }
}
