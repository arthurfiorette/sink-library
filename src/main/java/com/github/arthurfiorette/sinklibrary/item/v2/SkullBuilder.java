package com.github.arthurfiorette.sinklibrary.item.v2;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.UUID;
import lombok.experimental.UtilityClass;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;

@UtilityClass
public class SkullBuilder {

  /**
   * Create a itemBuilder from a OfflinePlayer head
   *
   * @param player the player
   *
   * @return a ItemBuilder instance from this player head
   */
  public static ItemBuilder ofPlayer(final OfflinePlayer player) {
    return SkullBuilder.ofGameProfile(new GameProfile(player.getUniqueId(), player.getName()));
  }

  /**
   * Create a itemBuilder from a player head
   *
   * @param playerName the player name
   *
   * @return a ItemBuilder instance from this player head
   */
  public static ItemBuilder ofHeadName(final String playerName) {
    return new ItemBuilder(Material.SKULL_ITEM)
      .durability((short) 3)
      .apply(
        (item, meta) -> {
          final SkullMeta sm = (SkullMeta) meta;
          sm.setOwner(playerName);
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
  public static ItemBuilder ofTextureUrl(final String url) {
    final GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    final byte[] encodedData = Base64.encodeBase64(
      String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes()
    );
    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
    return SkullBuilder.ofGameProfile(profile);
  }

  /**
   * Create a itemBuilder from a game profile head
   *
   * @param gameProfile the Mojang GameProfile to search for the head
   *
   * @return a ItemBuilder instance from this head
   */
  public static ItemBuilder ofGameProfile(final GameProfile gameProfile) {
    return new ItemBuilder(Material.SKULL_ITEM)
      .durability((short) 3)
      .apply(
        (item, meta) -> {
          try {
            final SkullMeta headMeta = (SkullMeta) meta;
            final Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, gameProfile);
          } catch (IllegalAccessException | NoSuchFieldException ignore) {}
        }
      );
  }
}
