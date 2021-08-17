package com.github.arthurfiorette.sinklibrary.replacer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bukkit.ChatColor;

@NoArgsConstructor
public class Replacer {

  @Getter
  @NonNull
  private final Map<String, Object> placeholders = new HashMap<>();

  public Replacer add(@NonNull final String key, @NonNull final Object value) {
    placeholders.put(key, value);
    return this;
  }

  public Replacer merge(@NonNull final Replacer other) {
    placeholders.putAll(other.placeholders);
    return this;
  }

  //

  public String replace(@NonNull String text) {
    for (final Entry<String, Object> entry : placeholders.entrySet()) {
      text = text.replaceAll(entry.getKey(), entry.getValue().toString().toString());
    }
    return text;
  }

  public String replace(@NonNull String text, final boolean alternateColorCodes) {
    text = this.replace(text);

    if (alternateColorCodes) {
      text = ChatColor.translateAlternateColorCodes('&', text);
    }

    return text;
  }

  //

  public static String replace(final String text, @NonNull final Function fn) {
    final Replacer replacer = fn.apply(new Replacer());
    return replacer.replace(text);
  }

  @FunctionalInterface
  public interface Function {
    Replacer apply(Replacer replacer);

    default Function add(final String placeholder, final Object value) {
      return r -> r.add(placeholder, value);
    }
  }
}
