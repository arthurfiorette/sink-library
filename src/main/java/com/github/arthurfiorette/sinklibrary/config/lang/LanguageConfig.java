package com.github.arthurfiorette.sinklibrary.config.lang;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.config.CustomConfig;
import com.github.arthurfiorette.sinklibrary.services.Replacer;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.bukkit.ChatColor;

public class LanguageConfig<E extends Enum<E> & PathResolver> extends CustomConfig {

  /**
   * The text sent when the value could not be found.
   */
  protected String unknownValue = ChatColor.RED + "Unknown value";

  public LanguageConfig(BasePlugin plugin, String resourcePath, boolean replaceIfExists) {
    super(plugin, resourcePath, replaceIfExists);
  }

  /**
   * Returns the translation found and apply a replacer in it
   *
   * @param lang the lang enum
   * @param replacer the text replacer to apply in the text.
   *
   * @return the text found or {@link LanguageFile#unknown()} if an error
   * occurs.
   */
  public String asText(E lang, UnaryOperator<Replacer> replacer) {
    return Replacer.replace(this.asText(lang), replacer);
  }

  /**
   * Returns the translation found
   *
   * @param lang the lang enum
   *
   * @return the text found or {@link LanguageFile#unknown()} if an error
   * occurs.
   */
  public String asText(E lang) {
    String text = this.getConfig().getString(lang.getPath());
    if (text == null) {
      this.plugin.log(
          Level.WARNING,
          "%s: %s is returning null.",
          this.getClass().getSimpleName(),
          lang.getPath()
        );
    }
    return text;
  }

  /**
   * Returns the translation list found and apply a replacer in it.
   *
   * @param lang the lang enum
   * @param replacer the text replacer to apply in the text.
   *
   * @return the text list found or {@link LanguageFile#unknown()} if an error
   * occurs.
   */
  public List<String> asList(E lang, UnaryOperator<Replacer> replacer) {
    return this.asList(lang)
      .stream()
      .map(str -> Replacer.replace(str, replacer))
      .collect(Collectors.toList());
  }

  /**
   * Returns the translation list found.
   *
   * @param lang the lang enum
   *
   * @return the text list found or {@link LanguageFile#unknown()} if an error
   * occurs.
   */
  public List<String> asList(E lang) {
    List<String> list = this.getConfig().getStringList(lang.getPath());

    if (list == null || list.isEmpty()) {
      this.plugin.log(
          Level.WARNING,
          "%s: %s is empty or null.",
          this.getClass().getSimpleName(),
          lang.getPath()
        );
      return Lists.newArrayList(this.unknownValue);
    }

    return list;
  }
}
