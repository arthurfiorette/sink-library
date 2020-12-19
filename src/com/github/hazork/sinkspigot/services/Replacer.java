package com.github.hazork.sinkspigot.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.bukkit.OfflinePlayer;

import com.github.hazork.sinkspigot.services.utils.SpigotServices;

import me.clip.placeholderapi.PlaceholderAPI;

public class Replacer {

    private Map<String, Supplier<String>> placeholders = new HashMap<>();

    public Replacer add(String placeholder, String value) {
	return add(placeholder, () -> value);
    }

    public Replacer add(String placeholder, Supplier<String> supplier) {
	placeholders.put(placeholder, supplier);
	return this;
    }

    public String replace(String str) {
	String replaced = str;
	for(Entry<String, Supplier<String>> entry: placeholders.entrySet()) {
	    replaced = replaced.replace(entry.getKey(), entry.getValue().get());
	}
	return SpigotServices.setColors(replaced);
    }

    public String replace(String str, OfflinePlayer player) {
	String text = str;
	if (canUsePlaceholderAPI()) {
	    text = PlaceholderAPI.setPlaceholders(player, replace(str));
	}
	return replace(text);
    }

    public static String replace(String str, UnaryOperator<Replacer> replacer) {
	return replacer.apply(new Replacer()).replace(str);
    }

    public static String replace(String str, OfflinePlayer player, UnaryOperator<Replacer> replacer) {
	return replacer.apply(new Replacer()).replace(str, player);
    }

    public static boolean canUsePlaceholderAPI() {
	return SpigotServices.hasPlugin("PlaceholderAPI");
    }

}
