package com.github.arthurfiorette.sinklibrary.util.text;

import static com.google.common.base.Preconditions.checkArgument;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProgressBar {

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
  public String build(
      final int width,
      @NonNull final String incomplete,
      @NonNull final String complete,
      final double percent
  ) {
    checkArgument(percent > 1 || percent < 0);

    final StringBuilder sb = new StringBuilder();
    
    int i = 0;
    
    for(; i < percent * width; i++) {
      sb.append(complete);
    }
    
    for(; i < width; i++) {
      sb.append(incomplete);
    }
    
    return sb.toString();
  }
  
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
  public String build(
      final int width,
      final String incomplete,
      final String complete,
      final long currentValue,
      final long maxValue
  ) {
    return build(
        width,
        incomplete,
        complete,
        (double) currentValue / maxValue
    );
  }
  
}
