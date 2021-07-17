package com.github.arthurfiorette.sinklibrary.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;

/**
 * A service class that handles with texts.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
@UtilityClass
public final class TextService {

  public final String LINE_SEPARATOR =
    "a9f879156392911e229ffccd7618ed88213f293f472ca5eb5e79da1bb698dc45"; // S2

  /**
   * Returns a progress bar in String.
   * <p>
   * {@code buildProgressBar(10, #, *, 6, 10)} will return {@code######****}.
   * <p>
   * Same as {@link TextService#buildProgressBar(int, String, String, double)}.
   *
   * @param width the width of the progress bar
   * @param incomplete the text placed when your representative percentage is
   * incomplete
   * @param complete the text placed when your representative percentage is
   * completed
   * @param currentValue the progress value
   * @param maxValue the highest possible value
   *
   * @return the progress bar string
   *
   * @see TextService#buildProgressBar(int, String, String, double)
   */
  public String buildProgressBar(
    final int width,
    final String incomplete,
    final String complete,
    final long currentValue,
    final long maxValue
  ) {
    return TextService.buildProgressBar(
      width,
      incomplete,
      complete,
      (double) currentValue / maxValue
    );
  }

  /**
   * Returns a progress bar in string.
   * <p>
   * {@code buildProgressBar(10, #, *, 0.6)} will return {@code######****}.
   *
   * @param width the width of the progress bar
   * @param incomplete the text placed when your representative percentage is
   * incomplete
   * @param complete the text placed when your representative percentage is
   * completed
   * @param percent the percentage value to build the bar
   *
   * @return the progress bar string
   */
  public String buildProgressBar(
    final int width,
    final String incomplete,
    final String complete,
    final double percent
  ) {
    if (percent > 1 || percent < 0) {
      throw new IllegalArgumentException("Percent should be between 1 and 0");
    }
    final StringBuilder sb = new StringBuilder();
    double i = 0;
    for (; i < percent * width; i++) {
      sb.append(complete);
    }
    for (; i < width; i++) {
      sb.append(incomplete);
    }
    return sb.toString();
  }

  /**
   * Split any minecraft colored text into sized lines without losing the color.
   *
   * @param text the raw text
   * @param width the width to split the lines
   *
   * @return the list with splitted lines
   */
  public List<String> splitTextColored(final String text, final int width) {
    return TextService.splitText(TextService.forceColor(text), width, "");
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
  public List<String> splitText(final String text, final int width, final String newLine) {
    return Arrays.asList(
      WordUtils
        .wrap(text, width, TextService.LINE_SEPARATOR + newLine, false)
        .split(TextService.LINE_SEPARATOR)
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
  public String forceColor(final String text) {
    final List<String> texts = new ArrayList<>();
    for (String str : text.split("§")) {
      if (str.isEmpty()) {
        continue;
      }
      if (!str.startsWith("§")) {
        str = "§" + str;
      }
      final Pattern notColor = Pattern.compile("§[^0123456789AaBbCcDdEeFfKkLlMmNnOoRr]");
      if (notColor.matcher(str).find()) {
        texts.add(str);
      } else {
        final String lastColor = ChatColor.getLastColors(str);
        final List<String> words = new ArrayList<>();
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
