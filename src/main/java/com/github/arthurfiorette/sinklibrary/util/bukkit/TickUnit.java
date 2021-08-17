package com.github.arthurfiorette.sinklibrary.util.bukkit;

import java.util.concurrent.TimeUnit;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TickUnit {

  
  /**
   * Convert milliseconds to minecraft ticks.
   * <p>
   * {@code milliseconds / 50 = ticks}
   *
   * @param milliseconds the time in milliseconds
   *
   * @return the amount of ticks into this time
   */
  public long from(final long milliseconds) {
    return milliseconds / 50;
  }
  
  /**
   * Convert any time to minecraft ticks time.
   *
   * @param unit the {@link TimeUnit}
   * @param value the time value
   *
   * @return the amount of ticks into this time
   */
  public long from(final TimeUnit unit, final long value) {
    return from(unit.toMillis(value));
  }

  public long asMilliseconds(final long ticks) {
    return ticks * 50;
  }

  /**
   * Convert any minecraft tick time to the provided unit.
   *
   * @param ticks the tick amount
   * @param unit the {@link TimeUnit}
   *
   * @return the amount of ticks into this time
   */
  public long to(final TimeUnit unit, final long ticks) {
    return unit.convert(asMilliseconds(ticks), TimeUnit.MILLISECONDS);
  }

}
