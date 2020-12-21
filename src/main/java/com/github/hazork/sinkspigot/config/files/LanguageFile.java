package com.github.hazork.sinkspigot.config.files;

import java.io.File;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.stream.Collectors;

import com.github.hazork.sinkspigot.SinkPlugin;
import com.github.hazork.sinkspigot.services.Replacer;
import com.google.common.collect.Lists;

/**
 * A language file is a yml file specified to handle translations. A good enum
 * to use in this class is a enum with a path value for every key, that can be
 * specified in {@link LanguageFile#path(Enum)}, so when you call
 * {@link LanguageFile#asText(Enum)} with {@code MyEnum.YES_TEXT} it will read
 * the path with this enum and search it in your yml file.
 *
 * @param <L> the enum that contains the keys and path for all the texts
 * @author https://github.com/Hazork/sink-library/
 */
public abstract class LanguageFile<L extends Enum<L>> extends CustomFile {

    public LanguageFile(SinkPlugin plugin, File folder, String name) {
	super(plugin, folder, name);
    }

    /**
     * Return the path associated with this enum.
     *
     * @param lang the enum
     * @return the path
     */
    protected abstract String path(L lang);

    /**
     * @return the default value when the path or text is unknwon or wrong.
     */
    protected String unknown() {
	return "Unknown value";
    }

    /**
     * Returns the translation found and apply a replacer in it
     *
     * @param lang     the lang enum
     * @param replacer the text replacer to apply in the text.
     * @return the text found or {@link LanguageFile#unknown()} if occour an
     *         error.
     */
    public String asText(L lang, UnaryOperator<Replacer> replacer) {
	return Replacer.replace(this.asText(lang), replacer);
    }

    /**
     * Returns the translation found
     *
     * @param lang the lang enum
     * @return the text found or {@link LanguageFile#unknown()} if occour an
     *         error.
     */
    public String asText(L lang) {
	String text = this.getConfig().getString(this.path(lang));
	this.logIf(text == null, "%s: %s is returning null.", this.getName(), this.path(lang));
	return text;
    }

    /**
     * Returns the translation list found and apply a replacer in it.
     *
     * @param lang     the lang enum
     * @param replacer the text replacer to apply in the text.
     * @return the text list found or {@link LanguageFile#unknown()} if occour
     *         an error.
     */
    public List<String> asList(L lang, UnaryOperator<Replacer> replacer) {
	return this.asList(lang).stream().map(str -> Replacer.replace(str, replacer)).collect(Collectors.toList());
    }

    /**
     * Returns the translation list found.
     *
     * @param lang the lang enum
     * @return the text list found or {@link LanguageFile#unknown()} if occour
     *         an error.
     */
    public List<String> asList(L lang) {
	List<String> list = this.getConfig().getStringList(this.path(lang));
	if (list == null || list.isEmpty()) {
	    this.logIf(true, "%s: %s is empty or null.", this.getName(), this.path(lang));
	    return Lists.newArrayList(this.unknown());
	}
	return list;
    }

    private void logIf(boolean cond, String message, Object... args) {
	if (cond) {
	    this.log(Level.INFO, message, args);
	}
    }
}
