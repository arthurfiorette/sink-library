package com.github.hazork.sinkapi.services.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;

public final class TextServices {

    private TextServices() {}

    public static String buildProgressBar(int width, String incomplete, String complete, long currentValue,
	    long maxValue) {
	return buildProgressBar(width, incomplete, complete, (((double) currentValue) / maxValue));
    }

    public static String buildProgressBar(int width, String incomplete, String complete, double percent) {
	if (percent > 1 || percent < 0) {
	    throw new IllegalArgumentException("Percent should be between 1 and 0");
	}
	StringBuilder sb = new StringBuilder();
	double i = 0;
	for (; i < (percent * width); i++) {
	    sb.append(complete);
	}
	for (; i < width; i++) {
	    sb.append(incomplete);
	}
	return sb.toString();
    }

    private static final String lineSeparator = "TextService:https://github.com/hazork:lineSeparator"; // S2

    public static List<String> splitTextColored(String text, int width) {
	return splitText(forceColor(text), width, "");
    }

    public static List<String> splitText(String text, int width, String newLine) {
	return Arrays.asList(WordUtils.wrap(text, width, lineSeparator + newLine, false).split(lineSeparator));
    }

    public static String forceColor(String text) {
	List<String> texts = new ArrayList<>();
	for (String str : text.split("§")) {
	    if (str.isEmpty()) {
		continue;
	    }
	    if (!str.startsWith("§")) {
		str = "§" + str;
	    }
	    Pattern isntColor = Pattern.compile("§[^0123456789AaBbCcDdEeFfKkLlMmNnOoRr]");
	    if (isntColor.matcher(str).find()) {
		texts.add(str);
	    } else {
		String lastColor = ChatColor.getLastColors(str);
		List<String> words = new ArrayList<>();
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
