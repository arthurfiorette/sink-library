package com.github.arthurfiorette.sinklibrary.util.text;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BukkitColors {

  public final char[] COLORS_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'a',
      'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O',
      'o', 'R', 'r' };

  /**
   * Force a text to be colored placing the color in every word..
   * <p>
   * Ex: {@code '§6Hello World§f!'} will return {@code '§6Hello §6World§f!'}.
   *
   * @param text the raw text
   * @param colorSeparator the color separator, usually {@code '§'} or
   * {@code '&'}
   *
   * @return the forced text
   */
  public String forceColor(final String text, final Separator separator) {
    final StringBuilder builder = new StringBuilder();
    final String colorSeparator = separator.regex();

    // colorLine is a word group with the same color in sequence
    for(final String colorLine: text.split("(?=" + colorSeparator + ")")) {
      if (colorLine.length() == 0) {
        continue;
      }

      // If the start of the word group is not colored, just add it to the group
      if (colorLine.charAt(0) != separator.character()) {
        builder.append(colorLine);
        continue;
      }

      final char colorChar = colorLine.charAt(1);
      final boolean isValidColor = isColorChar(colorChar);

      // If it isn't a valid color char, means that the text used the
      // colorSeparated as a plain text
      if (!isValidColor) {
        builder.append(colorLine);
        continue;
      }

      // Removes the first colorSeparator from the text.split
      final String escapedLine = colorLine.substring(2);

      // Replace every word if the color chars before it
      builder.append(escapedLine.replaceAll("\\w+", colorSeparator + colorChar + "$0"));
    }

    return builder.toString();
  }

  /**
   * Verify if this char is a bukkit color char.
   * 
   * @param c the char to check
   * 
   * @return true if it is
   * 
   * @see BukkitColors#COLORS_CHARS
   */
  public boolean isColorChar(final char c) {
    for(final char color: COLORS_CHARS) {
      if (c == color) {
        return true;
      }
    }
    return false;
  }

  @RequiredArgsConstructor
  public enum Separator {
    SILCROW('§'),
    AMPERSAND('&');

    @Getter
    @Accessors(fluent = true)
    private final char character;

    public String regex() {
      return "//" + character;
    }
  }
}
