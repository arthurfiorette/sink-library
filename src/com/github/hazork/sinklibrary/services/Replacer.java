package com.github.hazork.sinklibrary.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.bukkit.OfflinePlayer;

import com.github.hazork.sinklibrary.services.utils.SpigotServices;

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
	for (Entry<String, Supplier<String>> entry : placeholders.entrySet()) {
	    replaced = replaced.replace(entry.getKey(), entry.getValue().get());
	}
	return replaced;
    }

    public String replace(String str, OfflinePlayer player) {
	if (canUsePlaceholderAPI()) {
	    return PlaceholderAPI.setPlaceholders(player, replace(str));
	} else {
	    return replace(str);
	}
    }

    public static void replace(String str, UnaryOperator<Replacer> replacer) {
	replacer.apply(new Replacer()).replace(str);
    }

    public static void replace(String str, OfflinePlayer player, UnaryOperator<Replacer> replacer) {
	replacer.apply(new Replacer()).replace(str, player);
    }

    public static boolean canUsePlaceholderAPI() {
	return SpigotServices.hasPlugin("PlaceholderAPI");
    }

}
