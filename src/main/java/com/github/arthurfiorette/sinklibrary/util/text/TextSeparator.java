package com.github.arthurfiorette.sinklibrary.util.text;

import com.github.arthurfiorette.sinklibrary.util.text.BukkitColors.Separator;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TextSeparator {

  /**
   * Split any text into sized lines.
   *
   * @param text the raw text
   * @param maxWidth the width to split the lines
   * @param newLineText the text to be placed on every new line
   *
   * @return the list with splitted lines
   */
  public List<String> separate(@NonNull final String text, final int maxWidth) {
    final List<String> lines = new ArrayList<>();

    final StringBuilder builder = new StringBuilder();
    for (final String word : text.split("(?=\\s+)")) {
      final int actualLength = builder.length();

      if (word.length() + actualLength <= maxWidth) {
        builder.append(word);
        continue;
      }

      // Add it to the list and clear the actual builder
      lines.add(builder.toString());
      builder.setLength(0);
    }

    return lines;
  }

  /**
   * Split any text into sized lines.
   *
   * @param text the raw text
   * @param maxWidth the width to split the lines
   * @param newLineText the text to be placed on every new line
   *
   * @return the list with splitted lines
   */
  public List<String> separateColored(
    @NonNull final String text,
    final int maxWidth,
    final Separator separator
  ) {
    return separate(BukkitColors.forceColor(text, separator), maxWidth);
  }
}
