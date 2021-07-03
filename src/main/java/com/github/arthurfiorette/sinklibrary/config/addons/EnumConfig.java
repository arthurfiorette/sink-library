package com.github.arthurfiorette.sinklibrary.config.addons;

import com.github.arthurfiorette.sinklibrary.config.BaseConfig;
import com.github.arthurfiorette.sinklibrary.services.Replacer;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public interface EnumConfig<E extends Enum<E> & PathResolver> extends BaseConfig {
  /**
   * @see {@link ConfigurationSection#contains(String)}
   */
  default boolean contains(final E path) {
    return this.getConfig().contains(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#get(String)}
   */
  default Object get(final E path) {
    return this.getConfig().get(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#get(String, Object)}
   */
  default Object get(final E path, final Object def) {
    return this.getConfig().get(path.getPath(), def);
  }

  /**
   * @see {@link ConfigurationSection#set(String, Object)}
   */
  default void set(final E path, final Object value) {
    this.getConfig().set(path.getPath(), value);
  }

  /**
   * @see {@link ConfigurationSection#createSection(String)}
   */
  default ConfigurationSection createSection(final E path) {
    return this.getConfig().createSection(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#createSection(String, Map)}
   */
  default ConfigurationSection createSection(final E path, final Map<?, ?> map) {
    return this.getConfig().createSection(path.getPath(), map);
  }

  /**
   * @see {@link ConfigurationSection#getString(String)}
   */
  default String getString(final E path) {
    return this.getConfig().getString(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getString(String, String)}
   */
  default String getString(final E path, final String def) {
    return this.getConfig().getString(path.getPath(), def);
  }

  default String getString(final E lang, final UnaryOperator<Replacer> replacer) {
    return Replacer.replace(this.getString(lang), replacer);
  }

  default String getString(final E lang, final String def, final UnaryOperator<Replacer> replacer) {
    return Replacer.replace(this.getString(lang, def), replacer);
  }

  /**
   * @see {@link ConfigurationSection#isString(String)}
   */
  default boolean isString(final E path) {
    return this.getConfig().isString(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getInt(String)}
   */
  default int getInt(final E path) {
    return this.getConfig().getInt(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getInt(String, int)}
   */
  default int getInt(final E path, final int def) {
    return this.getConfig().getInt(path.getPath(), def);
  }

  /**
   * @see {@link ConfigurationSection#isInt(String)}
   */
  default boolean isInt(final E path) {
    return this.getConfig().isInt(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getBoolean(String)}
   */
  default boolean getBoolean(final E path, final boolean def) {
    return this.getConfig().getBoolean(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getBoolean(String, boolean)}
   */
  default boolean isBoolean(final E path) {
    return this.getConfig().isBoolean(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#isBoolean(String)}
   */
  default double getDouble(final E path) {
    return this.getConfig().getDouble(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getDouble(String,def)}
   */
  default double getDouble(final E path, final double def) {
    return this.getConfig().getDouble(path.getPath(), def);
  }

  /**
   * @see {@link ConfigurationSection#isDouble(String)}
   */
  default boolean isDouble(final E path) {
    return this.getConfig().isDouble(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getLong(String)}
   */
  default long getLong(final E path) {
    return this.getConfig().getLong(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getLong(String, long)}
   */
  default long getLong(final E path, final long def) {
    return this.getConfig().getLong(path.getPath(), def);
  }

  /**
   * @see {@link ConfigurationSection#isLong(String)}
   */
  default boolean isLong(final E path) {
    return this.getConfig().isLong(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#isList(String)}
   */
  default List<?> getList(final E path) {
    return this.getConfig().getList(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getList(String, List)}
   */
  default List<?> getList(final E path, final List<?> def) {
    return this.getConfig().getList(path.getPath(), def);
  }

  /**
   * @see {@link ConfigurationSection#isList(String)}
   */
  default boolean isList(final E path) {
    return this.getConfig().isList(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getStringList(String)}
   */
  default List<String> getStringList(final E path) {
    return this.getConfig().getStringList(path.getPath());
  }

  default List<String> getStringList(final E lang, final UnaryOperator<Replacer> replacer) {
    return this.getStringList(lang)
      .stream()
      .map(str -> Replacer.replace(str, replacer))
      .collect(Collectors.toList());
  }

  /**
   * @see {@link ConfigurationSection#getIntegerList(String)}
   */
  default List<Integer> getIntegerList(final E path) {
    return this.getConfig().getIntegerList(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getBooleanList(String)}
   */
  default List<Boolean> getBooleanList(final E path) {
    return this.getConfig().getBooleanList(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getDoubleList(String)}
   */
  default List<Double> getDoubleList(final E path) {
    return this.getConfig().getDoubleList(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getFloatList(String)}
   */
  default List<Float> getFloatList(final E path) {
    return this.getConfig().getFloatList(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getLongList(String)}
   */
  default List<Long> getLongList(final E path) {
    return this.getConfig().getLongList(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getByteList(String)}
   */
  default List<Byte> getByteList(final E path) {
    return this.getConfig().getByteList(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getCharacterList(String)}
   */
  default List<Character> getCharacterList(final E path) {
    return this.getConfig().getCharacterList(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getShortList(String)}
   */
  default List<Short> getShortList(final E path) {
    return this.getConfig().getShortList(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getMapList(String)}
   */
  default List<Map<?, ?>> getMapList(final E path) {
    return this.getConfig().getMapList(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getVector(String)v}
   */
  default Vector getVector(final E path) {
    return this.getConfig().getVector(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getVector(String, Vector)}
   */
  default Vector getVector(final E path, final Vector def) {
    return this.getConfig().getVector(path.getPath(), def);
  }

  /**
   * @see {@link ConfigurationSection#isVector(String)}
   */
  default boolean isVector(final E path) {
    return this.getConfig().isVector(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getOfflinePlayer(String)}
   */
  default OfflinePlayer getOfflinePlayer(final E path) {
    return this.getConfig().getOfflinePlayer(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getOfflinePlayer(String, OfflinePlayer)f}
   */
  default OfflinePlayer getOfflinePlayer(final E path, final OfflinePlayer def) {
    return this.getConfig().getOfflinePlayer(path.getPath(), def);
  }

  /**
   * @see {@link ConfigurationSection#isOfflinePlayer(String)}
   */
  default boolean isOfflinePlayer(final E path) {
    return this.getConfig().isOfflinePlayer(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getItemStack(String)}
   */
  default ItemStack getItemStack(final E path) {
    return this.getConfig().getItemStack(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getItemStack(String, ItemStack)}
   */
  default ItemStack getItemStack(final E path, final ItemStack def) {
    return this.getConfig().getItemStack(path.getPath(), def);
  }

  /**
   * @see {@link ConfigurationSection#isItemStack(String)}
   */
  default boolean isItemStack(final E path) {
    return this.getConfig().isItemStack(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getColor(String)}
   */
  default Color getColor(final E path) {
    return this.getConfig().getColor(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getColor(String, Color)}
   */
  default Color getColor(final E path, final Color def) {
    return this.getConfig().getColor(path.getPath(), def);
  }

  /**
   * @see {@link ConfigurationSection#isColor(String)}
   */
  default boolean isColor(final E path) {
    return this.getConfig().isColor(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#getConfigurationSection(String)}
   */
  default ConfigurationSection getConfigurationSection(final E path) {
    return this.getConfig().getConfigurationSection(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#isConfigurationSection(String)}
   */
  default boolean isConfigurationSection(final E path) {
    return this.getConfig().isConfigurationSection(path.getPath());
  }

  /**
   * @see {@link ConfigurationSection#addDefault(String, Object)}
   */
  default void addDefault(final E path, final Object value) {
    this.getConfig().addDefault(path.getPath(), value);
  }
}
