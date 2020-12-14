package com.github.hazork.sinklibrary.item;

import java.lang.reflect.Field;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class ItemBuilders {

    public static ItemBuilder ofHead(OfflinePlayer player) {
	return ofSkullGameProfile(new GameProfile(player.getUniqueId(), player.getName()));
    }

    public static ItemBuilder ofHead(String playername) {
	ItemBuilder builder = new ItemBuilder(Material.SKULL_ITEM).setDurability(3);
	return builder.addCustomMeta(im -> {
	    SkullMeta sm = (SkullMeta) im;
	    sm.setOwner(playername);
	    return sm;
	});
    }

    public static ItemBuilder ofHeadUrl(String url) {
	GameProfile profile = new GameProfile(UUID.randomUUID(), null);
	byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
	profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
	return ofSkullGameProfile(profile);
    }

    public static ItemBuilder ofSkullGameProfile(GameProfile gp) {
	ItemBuilder builder = new ItemBuilder(Material.SKULL_ITEM).setDurability(3);
	builder.addCustomMeta(meta -> {
	    try {
		SkullMeta headMeta = (SkullMeta) meta;
		Field profileField = headMeta.getClass().getDeclaredField("profile");
		profileField.setAccessible(true);
		profileField.set(headMeta, gp);
		return headMeta;
	    } catch (IllegalAccessException | NoSuchFieldException restore) {
		return meta;
	    }
	});
	return builder;
    }

    public static ItemBuilder getEmpty() {
	return new ItemBuilder(Material.AIR);
    }

}
