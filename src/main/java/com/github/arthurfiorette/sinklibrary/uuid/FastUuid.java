package com.github.arthurfiorette.sinklibrary.uuid;

import java.util.Arrays;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * Modified to include the mojang id variant and to use lombok
 * <p>
 * A utility class for quickly and efficiently parsing {@link java.util.UUID}
 * instances from strings and writing UUID instances as strings. The methods
 * contained in this class are optimized for speed and to minimize garbage
 * collection pressure. In benchmarks, {@link #parseUUID(CharSequence)} is a
 * little more than 14 times faster than {@link UUID#fromString(String)}, and
 * {@link #toString(UUID)} is a little more than six times faster than
 * {@link UUID#toString()} when compared to the implementations in Java 8 and
 * older. Under Java 9 and newer, {@link #parseUUID(CharSequence)} is about six
 * times faster than the JDK implementation and {@link #toString(UUID)} does not
 * offer any performance enhancements (or regressions!).
 * <p>
 *
 * @author <a href="https://github.com/jchambers/">Jon Chambers</a>
 *
 * @see <a href="https://github.com/jchambers/fast-uuid">FastUUID Github</a>
 */
@UtilityClass
public class FastUuid {

  public static final boolean USE_JDK_UUID_TO_STRING;

  public static final int UUID_STRING_LENGTH = 36;

  // Minus 4 dashes chars
  public static final int MOJANG_ID_LENGTH = FastUuid.UUID_STRING_LENGTH - 4;

  public static final char[] HEX_DIGITS = {
    '0',
    '1',
    '2',
    '3',
    '4',
    '5',
    '6',
    '7',
    '8',
    '9',
    'a',
    'b',
    'c',
    'd',
    'e',
    'f'
  };

  public static final long[] HEX_VALUES = new long[128];

  static {
    int majorVersion = 0;

    try {
      majorVersion = Integer.parseInt(System.getProperty("java.specification.version"));
    } catch (final NumberFormatException ignored) {
      // This will happen for pretty much anything before Java 9, which had a
      // version scheme like "1.8" instead of just "8".
    }

    USE_JDK_UUID_TO_STRING = majorVersion >= 9;

    Arrays.fill(FastUuid.HEX_VALUES, -1);
    FastUuid.HEX_VALUES['0'] = 0x0;
    FastUuid.HEX_VALUES['1'] = 0x1;
    FastUuid.HEX_VALUES['2'] = 0x2;
    FastUuid.HEX_VALUES['3'] = 0x3;
    FastUuid.HEX_VALUES['4'] = 0x4;
    FastUuid.HEX_VALUES['5'] = 0x5;
    FastUuid.HEX_VALUES['6'] = 0x6;
    FastUuid.HEX_VALUES['7'] = 0x7;
    FastUuid.HEX_VALUES['8'] = 0x8;
    FastUuid.HEX_VALUES['9'] = 0x9;

    FastUuid.HEX_VALUES['a'] = 0xa;
    FastUuid.HEX_VALUES['b'] = 0xb;
    FastUuid.HEX_VALUES['c'] = 0xc;
    FastUuid.HEX_VALUES['d'] = 0xd;
    FastUuid.HEX_VALUES['e'] = 0xe;
    FastUuid.HEX_VALUES['f'] = 0xf;

    FastUuid.HEX_VALUES['A'] = 0xa;
    FastUuid.HEX_VALUES['B'] = 0xb;
    FastUuid.HEX_VALUES['C'] = 0xc;
    FastUuid.HEX_VALUES['D'] = 0xd;
    FastUuid.HEX_VALUES['E'] = 0xe;
    FastUuid.HEX_VALUES['F'] = 0xf;
  }

  /**
   * Parses a UUID from the given character sequence. The character sequence
   * must represent a UUID as described in {@link UUID#toString()}.
   *
   * @param uuidSequence the character sequence from which to parse a UUID
   *
   * @return the UUID represented by the given character sequence
   *
   * @throws IllegalArgumentException if the given character sequence does not
   * conform to the string representation as described in
   * {@link UUID#toString()}
   */
  public UUID parseUUID(@NonNull final CharSequence uuidSequence) {
    if (
      uuidSequence.length() != FastUuid.UUID_STRING_LENGTH ||
      uuidSequence.charAt(8) != '-' ||
      uuidSequence.charAt(13) != '-' ||
      uuidSequence.charAt(18) != '-' ||
      uuidSequence.charAt(23) != '-'
    ) {
      throw new IllegalArgumentException("Illegal UUID string: " + uuidSequence);
    }

    long mostSignificantBits = FastUuid.getHexValueForChar(uuidSequence.charAt(0)) << 60;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(1)) << 56;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(2)) << 52;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(3)) << 48;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(4)) << 44;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(5)) << 40;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(6)) << 36;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(7)) << 32;

    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(9)) << 28;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(10)) << 24;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(11)) << 20;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(12)) << 16;

    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(14)) << 12;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(15)) << 8;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(16)) << 4;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(17));

    long leastSignificantBits =
      FastUuid.getHexValueForChar(uuidSequence.charAt(19)) << 60;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(20)) << 56;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(21)) << 52;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(22)) << 48;

    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(24)) << 44;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(25)) << 40;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(26)) << 36;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(27)) << 32;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(28)) << 28;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(29)) << 24;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(30)) << 20;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(31)) << 16;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(32)) << 12;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(33)) << 8;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(34)) << 4;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(35));

    return new UUID(mostSignificantBits, leastSignificantBits);
  }

  /**
   * Parses a Mojang Id from the given character sequence. The character
   * sequence must represent a UUID as an mojang id.
   * <p>
   * MojangId is the same format described in {@link UUID#toString()} except
   * that it does not contains dashes.
   *
   * @param uuidSequence the character sequence from which to parse a UUID
   *
   * @return the UUID represented by the given character sequence
   *
   * @throws IllegalArgumentException if the given character sequence does not
   * conform to the string representation as described in
   * {@link UUID#toString()}
   */
  public UUID parseMojangId(@NonNull final CharSequence uuidSequence) {
    if (uuidSequence.length() != FastUuid.MOJANG_ID_LENGTH) {
      throw new IllegalArgumentException("Illegal UUID string: " + uuidSequence);
    }

    long mostSignificantBits = FastUuid.getHexValueForChar(uuidSequence.charAt(0)) << 60;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(1)) << 56;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(2)) << 52;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(3)) << 48;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(4)) << 44;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(5)) << 40;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(6)) << 36;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(7)) << 32;

    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(8)) << 28;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(9)) << 24;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(10)) << 20;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(11)) << 16;

    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(12)) << 12;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(13)) << 8;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(14)) << 4;
    mostSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(15));

    long leastSignificantBits =
      FastUuid.getHexValueForChar(uuidSequence.charAt(16)) << 60;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(17)) << 56;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(18)) << 52;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(19)) << 48;

    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(20)) << 44;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(21)) << 40;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(22)) << 36;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(23)) << 32;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(24)) << 28;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(25)) << 24;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(26)) << 20;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(27)) << 16;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(28)) << 12;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(29)) << 8;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(30)) << 4;
    leastSignificantBits |= FastUuid.getHexValueForChar(uuidSequence.charAt(31));
    return new UUID(mostSignificantBits, leastSignificantBits);
  }

  /**
   * Returns a string representation of the given UUID. The returned string is
   * formatted as described in {@link UUID#toString()}.
   *
   * @param uuid the UUID to represent as a string
   *
   * @return a string representation of the given UUID
   */
  public String toString(@NonNull final UUID uuid) {
    if (FastUuid.USE_JDK_UUID_TO_STRING) {
      // OpenJDK 9 and newer use a fancy native approach to converting UUIDs to
      // strings and we're better off using
      // that if it's available.
      return uuid.toString();
    }

    final long mostSignificantBits = uuid.getMostSignificantBits();
    final long leastSignificantBits = uuid.getLeastSignificantBits();

    final char[] uuidChars = new char[FastUuid.UUID_STRING_LENGTH];

    uuidChars[0] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0xf000000000000000L) >>> 60)];
    uuidChars[1] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x0f00000000000000L) >>> 56)];
    uuidChars[2] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x00f0000000000000L) >>> 52)];
    uuidChars[3] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x000f000000000000L) >>> 48)];
    uuidChars[4] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x0000f00000000000L) >>> 44)];
    uuidChars[5] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x00000f0000000000L) >>> 40)];
    uuidChars[6] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x000000f000000000L) >>> 36)];
    uuidChars[7] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x0000000f00000000L) >>> 32)];
    uuidChars[8] = '-';
    uuidChars[9] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x00000000f0000000L) >>> 28)];
    uuidChars[10] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x000000000f000000L) >>> 24)];
    uuidChars[11] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x0000000000f00000L) >>> 20)];
    uuidChars[12] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x00000000000f0000L) >>> 16)];
    uuidChars[13] = '-';
    uuidChars[14] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x000000000000f000L) >>> 12)];
    uuidChars[15] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x0000000000000f00L) >>> 8)];
    uuidChars[16] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x00000000000000f0L) >>> 4)];
    uuidChars[17] =
      FastUuid.HEX_DIGITS[(int) (mostSignificantBits & 0x000000000000000fL)];
    uuidChars[18] = '-';
    uuidChars[19] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0xf000000000000000L) >>> 60)];
    uuidChars[20] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x0f00000000000000L) >>> 56)];
    uuidChars[21] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x00f0000000000000L) >>> 52)];
    uuidChars[22] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x000f000000000000L) >>> 48)];
    uuidChars[23] = '-';
    uuidChars[24] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x0000f00000000000L) >>> 44)];
    uuidChars[25] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x00000f0000000000L) >>> 40)];
    uuidChars[26] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x000000f000000000L) >>> 36)];
    uuidChars[27] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x0000000f00000000L) >>> 32)];
    uuidChars[28] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x00000000f0000000L) >>> 28)];
    uuidChars[29] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x000000000f000000L) >>> 24)];
    uuidChars[30] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x0000000000f00000L) >>> 20)];
    uuidChars[31] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x00000000000f0000L) >>> 16)];
    uuidChars[32] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x000000000000f000L) >>> 12)];
    uuidChars[33] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x0000000000000f00L) >>> 8)];
    uuidChars[34] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x00000000000000f0L) >>> 4)];
    uuidChars[35] =
      FastUuid.HEX_DIGITS[(int) (leastSignificantBits & 0x000000000000000fL)];

    return new String(uuidChars);
  }

  /**
   * Returns a string representation of the given UUID. The returned string is
   * formatted as an mojang id.
   * <p>
   * MojangId is the same format described in {@link UUID#toString()} except
   * that it does not contains dashes.
   *
   * @param uuid the UUID to represent as a string
   *
   * @return a string representation of the given UUID
   */
  public String toMojangId(@NonNull final UUID uuid) {
    final long mostSignificantBits = uuid.getMostSignificantBits();
    final long leastSignificantBits = uuid.getLeastSignificantBits();

    final char[] uuidChars = new char[FastUuid.MOJANG_ID_LENGTH];

    uuidChars[0] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0xf000000000000000L) >>> 60)];
    uuidChars[1] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x0f00000000000000L) >>> 56)];
    uuidChars[2] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x00f0000000000000L) >>> 52)];
    uuidChars[3] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x000f000000000000L) >>> 48)];
    uuidChars[4] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x0000f00000000000L) >>> 44)];
    uuidChars[5] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x00000f0000000000L) >>> 40)];
    uuidChars[6] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x000000f000000000L) >>> 36)];
    uuidChars[7] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x0000000f00000000L) >>> 32)];
    uuidChars[8] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x00000000f0000000L) >>> 28)];
    uuidChars[9] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x000000000f000000L) >>> 24)];
    uuidChars[10] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x0000000000f00000L) >>> 20)];
    uuidChars[11] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x00000000000f0000L) >>> 16)];
    uuidChars[12] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x000000000000f000L) >>> 12)];
    uuidChars[13] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x0000000000000f00L) >>> 8)];
    uuidChars[14] =
      FastUuid.HEX_DIGITS[(int) ((mostSignificantBits & 0x00000000000000f0L) >>> 4)];
    uuidChars[15] =
      FastUuid.HEX_DIGITS[(int) (mostSignificantBits & 0x000000000000000fL)];
    uuidChars[16] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0xf000000000000000L) >>> 60)];
    uuidChars[17] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x0f00000000000000L) >>> 56)];
    uuidChars[18] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x00f0000000000000L) >>> 52)];
    uuidChars[19] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x000f000000000000L) >>> 48)];
    uuidChars[20] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x0000f00000000000L) >>> 44)];
    uuidChars[21] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x00000f0000000000L) >>> 40)];
    uuidChars[22] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x000000f000000000L) >>> 36)];
    uuidChars[23] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x0000000f00000000L) >>> 32)];
    uuidChars[24] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x00000000f0000000L) >>> 28)];
    uuidChars[25] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x000000000f000000L) >>> 24)];
    uuidChars[26] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x0000000000f00000L) >>> 20)];
    uuidChars[27] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x00000000000f0000L) >>> 16)];
    uuidChars[28] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x000000000000f000L) >>> 12)];
    uuidChars[29] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x0000000000000f00L) >>> 8)];
    uuidChars[30] =
      FastUuid.HEX_DIGITS[(int) ((leastSignificantBits & 0x00000000000000f0L) >>> 4)];
    uuidChars[31] =
      FastUuid.HEX_DIGITS[(int) (leastSignificantBits & 0x000000000000000fL)];

    return new String(uuidChars);
  }

  public long getHexValueForChar(final char c) {
    try {
      if (FastUuid.HEX_VALUES[c] < 0) {
        throw new IllegalArgumentException("Illegal hexadecimal digit: " + c);
      }
    } catch (final ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Illegal hexadecimal digit: " + c);
    }

    return FastUuid.HEX_VALUES[c];
  }
}
