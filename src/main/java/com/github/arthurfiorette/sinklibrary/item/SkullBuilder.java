package com.github.arthurfiorette.sinklibrary.item;

import com.cryptomorin.xseries.ReflectionUtils;
import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

/**
 * An utility class to help with item builders with heads.
 * 
 * @see SkullUtils for more info
 */
@UtilityClass
public class SkullBuilder {

  private final MethodHandle PROFILE_SETTER;
  private final String TEXTURES_JSON = "{textures:{SKIN:{url:\"%s\"}}}";

  static {
    final MethodHandles.Lookup lookup = MethodHandles.lookup();
    MethodHandle profileSetter = null;

    try {
      final Class<?> CraftMetaSkull = ReflectionUtils.getCraftClass("inventory.CraftMetaSkull");
      final Field profile = CraftMetaSkull.getDeclaredField("profile");
      profile.setAccessible(true);

      profileSetter = lookup.unreflectSetter(profile);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }

    PROFILE_SETTER = profileSetter;
  }

  /**
   * Create a itemBuilder from a OfflinePlayer head
   *
   * @param player the player
   *
   * @return a ItemBuilder instance from this player head
   */
  public static ItemBuilder from(final OfflinePlayer player) {
    return new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial()).apply((item, meta) -> {
      SkullUtils.applySkin(meta, player);
    });
  }

  /**
   * Create a itemBuilder from a player uuid
   *
   * @param identifier the player uuid
   *
   * @return a ItemBuilder instance from a player uuid
   */
  public static ItemBuilder from(final UUID identifier) {
    return from(Bukkit.getOfflinePlayer(identifier));
  }

  /**
   * Create a itemBuilder from a player head
   *
   * @param name the player name
   *
   * @return a ItemBuilder instance from this player head
   */
  public static ItemBuilder from(final String name) {
    return new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial()).apply((item, meta) -> {
      SkullUtils.applySkin(meta, name);
    });
  }

  /**
   * Create a itemBuilder from a url texture head
   *
   * @param url the url where the texture is
   *
   * @return a ItemBuilder instance from this head
   */
  public static ItemBuilder fromTexture(final String url) {
    final GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    final String encodedData = encodeBase64(String.format(TEXTURES_JSON, url));

    profile.getProperties().put("textures", new Property("textures", encodedData));

    return new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial()).apply((item, meta) -> {
      setProfile(meta, profile);
    });
  }

  @SneakyThrows
  private static void setProfile(final ItemMeta meta, final GameProfile profile) {
    PROFILE_SETTER.invoke(meta, profile);
  }

  private static String encodeBase64(@NonNull final String str) {
    return Base64.getEncoder().encodeToString(str.getBytes());
  }
}
