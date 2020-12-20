package com.github.hazork.sinkspigot.config.files;

import java.io.File;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.stream.Collectors;

import com.github.hazork.sinkspigot.SinkPlugin;
import com.github.hazork.sinkspigot.services.Replacer;

public abstract class LanguageFile<L extends Enum<L>> extends CustomFile {

    public LanguageFile(SinkPlugin plugin, File folder, String name) {
	super(plugin, folder, name);
    }

    protected abstract String path(L lang);

    protected String unknown() {
	return "Unknown value";
    }

    public String asText(L lang, UnaryOperator<Replacer> replacer) {
	return Replacer.replace(asText(lang), replacer);
    }

    public String asText(L lang) {
	String text = getConfig().getString(path(lang));
	logIf(text == null, "%s: %s is returning null.", getName(), path(lang));
	return text;
    }

    public List<String> asList(L lang, UnaryOperator<Replacer> replacer) {
	return asList(lang).stream().map(str -> Replacer.replace(str, replacer)).collect(Collectors.toList());
    }

    public List<String> asList(L lang) {
	List<String> list = getConfig().getStringList(path(lang));
	logIf(list == null || list.isEmpty(), "%s: %s is empty or null.", getName(), path(lang));
	return list;
    }

    private void logIf(boolean cond, String message, Object... args) {
	if (cond) {
	    log(Level.INFO, message, args);
	}
    }
}
