package com.github.arthurfiorette.sinklibrary.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * A static class to help with item builder templates.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public class ItemBuilders {

  /**
   * Create a itemBuilder from a OfflinePlayer head
   *
   * @param player the player
   *
   * @return a ItemBuilder instance from this player head
   */
  public static ItemBuilder ofHead(OfflinePlayer player) {
    return ofSkullGameProfile(new GameProfile(player.getUniqueId(), player.getName()));
  }

  /**
   * Create a itemBuilder from a player head
   *
   * @param playerName the player name
   *
   * @return a ItemBuilder instance from this player head
   */
  public static ItemBuilder ofHead(String playerName) {
    ItemBuilder builder = new ItemBuilder(Material.SKULL_ITEM).setDurability(3);
    return builder.addCustomMeta(
      im -> {
        SkullMeta sm = (SkullMeta) im;
        sm.setOwner(playerName);
        return sm;
      }
    );
  }

  /**
   * Create a itemBuilder from a url texture head
   *
   * @param url the url where the texture is
   *
   * @return a ItemBuilder instance from this head
   */
  public static ItemBuilder ofHeadUrl(String url) {
    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    byte[] encodedData = Base64.encodeBase64(
      String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes()
    );
    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
    return ofSkullGameProfile(profile);
  }

  /**
   * Create a itemBuilder from a game profile head
   *
   * @param gameProfile the Mojang GameProfile to search for the head
   *
   * @return a ItemBuilder instance from this head
   */
  public static ItemBuilder ofSkullGameProfile(GameProfile gameProfile) {
    ItemBuilder builder = new ItemBuilder(Material.SKULL_ITEM).setDurability(3);
    builder.addCustomMeta(
      meta -> {
        try {
          SkullMeta headMeta = (SkullMeta) meta;
          Field profileField = headMeta.getClass().getDeclaredField("profile");
          profileField.setAccessible(true);
          profileField.set(headMeta, gameProfile);
          return headMeta;
        } catch (IllegalAccessException | NoSuchFieldException restore) {
          return meta;
        }
      }
    );
    return builder;
  }

  /**
   * @return a empty item builder
   */
  public static ItemBuilder empty() {
    return new ItemBuilder(Material.AIR);
  }
}
