package com.github.arthurfiorette.sinklibrary.config.addons;

import com.github.arthurfiorette.sinklibrary.config.BaseConfig;
import com.github.arthurfiorette.sinklibrary.replacer.Replacer;
import com.github.arthurfiorette.sinklibrary.replacer.ReplacerFunction;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.MemorySection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public interface EnumConfig<P extends Enum<P> & PathResolver> extends BaseConfig {
  /**
   * Checks if this {@link ConfigurationSection} contains the given path.
   * <p>
   * If the value for the requested path does not exist but a default value has
   * been specified, this will return true.
   *
   * @param path Path to check for existence.
   *
   * @return True if this section contains the requested path, either via
   * default or being set.
   *
   * @throws IllegalArgumentException Thrown when path is null.
   */
  default boolean contains(final P path) {
    return this.getConfig().contains(path.getPath());
  }

  /**
   * Gets the requested Object by path.
   * <p>
   * If the Object does not exist but a default value has been specified, this
   * will return the default value. If the Object does not exist and no default
   * value was specified, this will return null.
   *
   * @param path Path of the Object to get.
   *
   * @return Requested Object.
   */
  default Object get(final P path) {
    return this.getConfig().get(path.getPath());
  }

  /**
   * Gets the requested Object by path, returning a default value if not found.
   * <p>
   * If the Object does not exist then the specified default value will returned
   * regardless of if a default has been identified in the root
   * {@link Configuration}.
   *
   * @param path Path of the Object to get.
   * @param def The default value to return if the path is not found.
   *
   * @return Requested Object.
   */
  default Object get(final P path, final Object def) {
    return this.getConfig().get(path.getPath(), def);
  }

  /**
   * Sets the specified path to the given value.
   * <p>
   * If value is null, the entry will be removed. Any existing entry will be
   * replaced, regardless of what the new value is.
   * <p>
   * Some implementations may have limitations on what you may store. See their
   * individual javadocs for details. No implementations should allow you to
   * store {@link Configuration}s or {@link ConfigurationSection}s, please use
   * {@link EnumConfig#createSection(Enum)} for that.
   *
   * @param path Path of the object to set.
   * @param value New value to set the path to.
   */
  default void set(final P path, final Object value) {
    this.getConfig().set(path.getPath(), value);
  }

  /**
   * Creates an empty {@link ConfigurationSection} at the specified path.
   * <p>
   * Any value that was previously set at this path will be overwritten. If the
   * previous value was itself a {@link ConfigurationSection}, it will be
   * orphaned.
   *
   * @param path Path to create the section at.
   *
   * @return Newly created section
   */
  default ConfigurationSection createSection(final P path) {
    return this.getConfig().createSection(path.getPath());
  }

  /**
   * Creates a {@link ConfigurationSection} at the specified path, with
   * specified values.
   * <p>
   * Any value that was previously set at this path will be overwritten. If the
   * previous value was itself a {@link ConfigurationSection}, it will be
   * orphaned.
   *
   * @param path Path to create the section at.
   * @param map The values to used.
   *
   * @return Newly created section
   */
  default ConfigurationSection createSection(final P path, final Map<?, ?> map) {
    return this.getConfig().createSection(path.getPath(), map);
  }

  /**
   * Gets the requested String by path.
   * <p>
   * If the String does not exist but a default value has been specified, this
   * will return the default value. If the String does not exist and no default
   * value was specified, this will return null.
   *
   * @param path Path of the String to get.
   *
   * @return Requested String.
   */
  default String getString(final P path) {
    return this.getConfig().getString(path.getPath());
  }

  /**
   * Gets the requested String by path, returning a default value if not found.
   * <p>
   * If the String does not exist then the specified default value will returned
   * regardless of if a default has been identified in the root
   * {@link Configuration}.
   *
   * @param path Path of the String to get.
   * @param def The default value to return if the path is not found or is not a
   * String.
   *
   * @return Requested String.
   */
  default String getString(final P path, final String def) {
    return this.getConfig().getString(path.getPath(), def);
  }

  /**
   * Gets the requested String by path.
   * <p>
   * If the String does not exist but a default value has been specified, this
   * will return the default value. If the String does not exist and no default
   * value was specified, this will return null.
   *
   * @param path Path of the String to get.
   * @param replacer the replacer operator
   *
   * @return Requested String.
   */
  default String getString(final P path, final ReplacerFunction replacer) {
    return Replacer.replace(this.getString(path), replacer);
  }

  /**
   * Gets the requested String by path, returning a default value if not found.
   * <p>
   * If the String does not exist then the specified default value will returned
   * regardless of if a default has been identified in the root
   * {@link Configuration}.
   *
   * @param path Path of the String to get.
   * @param def The default value to return if the path is not found or is not a
   * String.
   * @param replacer the replacer operator
   *
   * @return Requested String.
   */
  default String getString(final P path, final String def, final ReplacerFunction replacer) {
    return Replacer.replace(this.getString(path, def), replacer);
  }

  /**
   * Checks if the specified path is a String.
   * <p>
   * If the path exists but is not a String, this will return false. If the path
   * does not exist, this will return false. If the path does not exist but a
   * default value has been specified, this will check if that default value is
   * a String and return appropriately.
   *
   * @param path Path of the String to check.
   *
   * @return Whether or not the specified path is a String.
   */
  default boolean isString(final P path) {
    return this.getConfig().isString(path.getPath());
  }

  /**
   * Gets the requested int by path.
   * <p>
   * If the int does not exist but a default value has been specified, this will
   * return the default value. If the int does not exist and no default value
   * was specified, this will return 0.
   *
   * @param path Path of the int to get.
   *
   * @return Requested int.
   */
  default int getInt(final P path) {
    return this.getConfig().getInt(path.getPath());
  }

  /**
   * Gets the requested int by path, returning a default value if not found.
   * <p>
   * If the int does not exist then the specified default value will returned
   * regardless of if a default has been identified in the root
   * {@link Configuration}.
   *
   * @param path Path of the int to get.
   * @param def The default value to return if the path is not found or is not
   * an int.
   *
   * @return Requested int.
   */
  default int getInt(final P path, final int def) {
    return this.getConfig().getInt(path.getPath(), def);
  }

  /**
   * Checks if the specified path is an int.
   * <p>
   * If the path exists but is not a int, this will return false. If the path
   * does not exist, this will return false. If the path does not exist but a
   * default value has been specified, this will check if that default value is
   * a int and return appropriately.
   *
   * @param path Path of the int to check.
   *
   * @return Whether or not the specified path is an int.
   */
  default boolean isInt(final P path) {
    return this.getConfig().isInt(path.getPath());
  }

  /**
   * Gets the requested boolean by path.
   * <p>
   * If the boolean does not exist but a default value has been specified, this
   * will return the default value. If the boolean does not exist and no default
   * value was specified, this will return false.
   *
   * @param path Path of the boolean to get.
   *
   * @return Requested boolean.
   */
  default boolean getBoolean(final P path) {
    return this.getConfig().getBoolean(path.getPath());
  }

  /**
   * Gets the requested boolean by path, returning a default value if not found.
   * <p>
   * If the boolean does not exist then the specified default value will
   * returned regardless of if a default has been identified in the root
   * {@link Configuration}.
   *
   * @param path Path of the boolean to get.
   * @param def The default value to return if the path is not found or is not a
   * boolean.
   *
   * @return Requested boolean.
   */
  default boolean getBoolean(final P path, final boolean def) {
    return this.getConfig().getBoolean(path.getPath(), def);
  }

  /**
   * Checks if the specified path is a boolean.
   * <p>
   * If the path exists but is not a boolean, this will return false. If the
   * path does not exist, this will return false. If the path does not exist but
   * a default value has been specified, this will check if that default value
   * is a boolean and return appropriately.
   *
   * @param path Path of the boolean to check.
   *
   * @return Whether or not the specified path is a boolean.
   */
  default boolean isBoolean(final P path) {
    return this.getConfig().isBoolean(path.getPath());
  }

  /**
   * Gets the requested double by path.
   * <p>
   * If the double does not exist but a default value has been specified, this
   * will return the default value. If the double does not exist and no default
   * value was specified, this will return 0.
   *
   * @param path Path of the double to get.
   *
   * @return Requested double.
   */
  default double getDouble(final P path) {
    return this.getConfig().getDouble(path.getPath());
  }

  /**
   * Gets the requested double by path, returning a default value if not found.
   * <p>
   * If the double does not exist then the specified default value will returned
   * regardless of if a default has been identified in the root
   * {@link Configuration}.
   *
   * @param path Path of the double to get.
   * @param def The default value to return if the path is not found or is not a
   * double.
   *
   * @return Requested double.
   */
  default double getDouble(final P path, final double def) {
    return this.getConfig().getDouble(path.getPath(), def);
  }

  /**
   * Checks if the specified path is a double.
   * <p>
   * If the path exists but is not a double, this will return false. If the path
   * does not exist, this will return false. If the path does not exist but a
   * default value has been specified, this will check if that default value is
   * a double and return appropriately.
   *
   * @param path Path of the double to check.
   *
   * @return Whether or not the specified path is a double.
   */
  default boolean isDouble(final P path) {
    return this.getConfig().isDouble(path.getPath());
  }

  /**
   * Checks if the specified path is a double.
   * <p>
   * If the path exists but is not a double, this will return false. If the path
   * does not exist, this will return false. If the path does not exist but a
   * default value has been specified, this will check if that default value is
   * a double and return appropriately.
   *
   * @param path Path of the double to check.
   *
   * @return Whether or not the specified path is a double.
   */
  default long getLong(final P path) {
    return this.getConfig().getLong(path.getPath());
  }

  /**
   * Gets the requested long by path, returning a default value if not found.
   * <p>
   * If the long does not exist then the specified default value will returned
   * regardless of if a default has been identified in the root
   * {@link Configuration}.
   *
   * @param path Path of the long to get.
   * @param def The default value to return if the path is not found or is not a
   * long.
   *
   * @return Requested long.
   */
  default long getLong(final P path, final long def) {
    return this.getConfig().getLong(path.getPath(), def);
  }

  /**
   * Checks if the specified path is a long.
   * <p>
   * If the path exists but is not a long, this will return false. If the path
   * does not exist, this will return false. If the path does not exist but a
   * default value has been specified, this will check if that default value is
   * a long and return appropriately.
   *
   * @param path Path of the long to check.
   *
   * @return Whether or not the specified path is a long.
   */
  default boolean isLong(final P path) {
    return this.getConfig().isLong(path.getPath());
  }

  /**
   * Gets the requested List by path.
   * <p>
   * If the List does not exist but a default value has been specified, this
   * will return the default value. If the List does not exist and no default
   * value was specified, this will return null.
   *
   * @param path Path of the List to get.
   *
   * @return Requested List.
   */
  default List<?> getList(final P path) {
    return this.getConfig().getList(path.getPath());
  }

  /**
   * Gets the requested List by path, returning a default value if not found.
   * <p>
   * If the List does not exist then the specified default value will returned
   * regardless of if a default has been identified in the root
   * {@link Configuration}.
   *
   * @param path Path of the List to get.
   * @param def The default value to return if the path is not found or is not a
   * List.
   *
   * @return Requested List.
   */
  default List<?> getList(final P path, final List<?> def) {
    return this.getConfig().getList(path.getPath(), def);
  }

  /**
   * Checks if the specified path is a List.
   * <p>
   * If the path exists but is not a List, this will return false. If the path
   * does not exist, this will return false. If the path does not exist but a
   * default value has been specified, this will check if that default value is
   * a List and return appropriately.
   *
   * @param path Path of the List to check.
   *
   * @return Whether or not the specified path is a List.
   */
  default boolean isList(final P path) {
    return this.getConfig().isList(path.getPath());
  }

  /**
   * Gets the requested List of String by path.
   * <p>
   * If the List does not exist but a default value has been specified, this
   * will return the default value. If the List does not exist and no default
   * value was specified, this will return an empty List.
   * <p>
   * This method will attempt to cast any values into a String if possible, but
   * may miss any values out if they are not compatible.
   *
   * @param path Path of the List to get.
   *
   * @return Requested List of String.
   */
  default List<String> getStringList(final P path) {
    return this.getConfig().getStringList(path.getPath());
  }

  /**
   * Gets the requested List of String by path.
   * <p>
   * If the List does not exist but a default value has been specified, this
   * will return the default value. If the List does not exist and no default
   * value was specified, this will return an empty List.
   * <p>
   * This method will attempt to cast any values into a String if possible, but
   * may miss any values out if they are not compatible.
   *
   * @param path Path of the List to get.
   * @param replacer the replacer operator
   *
   * @return Requested List of String.
   */
  default List<String> getStringList(final P path, final ReplacerFunction replacer) {
    return this.getStringList(path).stream().map(str -> Replacer.replace(str, replacer))
        .collect(Collectors.toList());
  }

  /**
   * Gets the requested List of Integer by path.
   * <p>
   * If the List does not exist but a default value has been specified, this
   * will return the default value. If the List does not exist and no default
   * value was specified, this will return an empty List.
   * <p>
   * This method will attempt to cast any values into a Integer if possible, but
   * may miss any values out if they are not compatible.
   *
   * @param path Path of the List to get.
   *
   * @return Requested List of Integer.
   */
  default List<Integer> getIntegerList(final P path) {
    return this.getConfig().getIntegerList(path.getPath());
  }

  /**
   * Gets the requested List of Boolean by path.
   * <p>
   * If the List does not exist but a default value has been specified, this
   * will return the default value. If the List does not exist and no default
   * value was specified, this will return an empty List.
   * <p>
   * This method will attempt to cast any values into a Boolean if possible, but
   * may miss any values out if they are not compatible.
   *
   * @param path Path of the List to get.
   *
   * @return Requested List of Boolean.
   */
  default List<Boolean> getBooleanList(final P path) {
    return this.getConfig().getBooleanList(path.getPath());
  }

  /**
   * Gets the requested List of Double by path.
   * <p>
   * If the List does not exist but a default value has been specified, this
   * will return the default value. If the List does not exist and no default
   * value was specified, this will return an empty List.
   * <p>
   * This method will attempt to cast any values into a Double if possible, but
   * may miss any values out if they are not compatible.
   *
   * @param path Path of the List to get.
   *
   * @return Requested List of Double.
   */
  default List<Double> getDoubleList(final P path) {
    return this.getConfig().getDoubleList(path.getPath());
  }

  /**
   * Gets the requested List of Float by path.
   * <p>
   * If the List does not exist but a default value has been specified, this
   * will return the default value. If the List does not exist and no default
   * value was specified, this will return an empty List.
   * <p>
   * This method will attempt to cast any values into a Float if possible, but
   * may miss any values out if they are not compatible.
   *
   * @param path Path of the List to get.
   *
   * @return Requested List of Float.
   */
  default List<Float> getFloatList(final P path) {
    return this.getConfig().getFloatList(path.getPath());
  }

  /**
   * Gets the requested List of Long by path.
   * <p>
   * If the List does not exist but a default value has been specified, this
   * will return the default value. If the List does not exist and no default
   * value was specified, this will return an empty List.
   * <p>
   * This method will attempt to cast any values into a Long if possible, but
   * may miss any values out if they are not compatible.
   *
   * @param path Path of the List to get.
   *
   * @return Requested List of Long.
   */
  default List<Long> getLongList(final P path) {
    return this.getConfig().getLongList(path.getPath());
  }

  /**
   * Gets the requested List of Byte by path.
   * <p>
   * If the List does not exist but a default value has been specified, this
   * will return the default value. If the List does not exist and no default
   * value was specified, this will return an empty List.
   * <p>
   * This method will attempt to cast any values into a Byte if possible, but
   * may miss any values out if they are not compatible.
   *
   * @param path Path of the List to get.
   *
   * @return Requested List of Byte.
   */
  default List<Byte> getByteList(final P path) {
    return this.getConfig().getByteList(path.getPath());
  }

  /**
   * Gets the requested List of Character by path.
   * <p>
   * If the List does not exist but a default value has been specified, this
   * will return the default value. If the List does not exist and no default
   * value was specified, this will return an empty List.
   * <p>
   * This method will attempt to cast any values into a Character if possible,
   * but may miss any values out if they are not compatible.
   *
   * @param path Path of the List to get.
   *
   * @return Requested List of Character.
   */
  default List<Character> getCharacterList(final P path) {
    return this.getConfig().getCharacterList(path.getPath());
  }

  /**
   * Gets the requested List of Short by path.
   * <p>
   * If the List does not exist but a default value has been specified, this
   * will return the default value. If the List does not exist and no default
   * value was specified, this will return an empty List.
   * <p>
   * This method will attempt to cast any values into a Short if possible, but
   * may miss any values out if they are not compatible.
   *
   * @param path Path of the List to get.
   *
   * @return Requested List of Short.
   */
  default List<Short> getShortList(final P path) {
    return this.getConfig().getShortList(path.getPath());
  }

  /**
   * Gets the requested List of Maps by path.
   * <p>
   * If the List does not exist but a default value has been specified, this
   * will return the default value. If the List does not exist and no default
   * value was specified, this will return an empty List.
   * <p>
   * This method will attempt to cast any values into a Map if possible, but may
   * miss any values out if they are not compatible.
   *
   * @param path Path of the List to get.
   *
   * @return Requested List of Maps.
   */
  default List<Map<?, ?>> getMapList(final P path) {
    return this.getConfig().getMapList(path.getPath());
  }

  /**
   * Gets the requested Vector by path.
   * <p>
   * If the Vector does not exist but a default value has been specified, this
   * will return the default value. If the Vector does not exist and no default
   * value was specified, this will return null.
   *
   * @param path Path of the Vector to get.
   *
   * @return Requested Vector.
   */
  default Vector getVector(final P path) {
    return this.getConfig().getVector(path.getPath());
  }

  /**
   * Gets the requested {@link Vector} by path, returning a default value if not
   * found.
   * <p>
   * If the Vector does not exist then the specified default value will returned
   * regardless of if a default has been identified in the root
   * {@link Configuration}.
   *
   * @param path Path of the Vector to get.
   * @param def The default value to return if the path is not found or is not a
   * Vector.
   *
   * @return Requested Vector.
   */
  default Vector getVector(final P path, final Vector def) {
    return this.getConfig().getVector(path.getPath(), def);
  }

  /**
   * Checks if the specified path is a Vector.
   * <p>
   * If the path exists but is not a Vector, this will return false. If the path
   * does not exist, this will return false. If the path does not exist but a
   * default value has been specified, this will check if that default value is
   * a Vector and return appropriately.
   *
   * @param path Path of the Vector to check.
   *
   * @return Whether or not the specified path is a Vector.
   */
  default boolean isVector(final P path) {
    return this.getConfig().isVector(path.getPath());
  }

  /**
   * Gets the requested OfflinePlayer by path.
   * <p>
   * If the OfflinePlayer does not exist but a default value has been specified,
   * this will return the default value. If the OfflinePlayer does not exist and
   * no default value was specified, this will return null.
   *
   * @param path Path of the OfflinePlayer to get.
   *
   * @return Requested OfflinePlayer.
   */
  default OfflinePlayer getOfflinePlayer(final P path) {
    return this.getConfig().getOfflinePlayer(path.getPath());
  }

  /**
   * Gets the requested {@link OfflinePlayer} by path, returning a default value
   * if not found.
   * <p>
   * If the OfflinePlayer does not exist then the specified default value will
   * returned regardless of if a default has been identified in the root
   * {@link Configuration}.
   *
   * @param path Path of the OfflinePlayer to get.
   * @param def The default value to return if the path is not found or is not
   * an OfflinePlayer.
   *
   * @return Requested OfflinePlayer.
   */
  default OfflinePlayer getOfflinePlayer(final P path, final OfflinePlayer def) {
    return this.getConfig().getOfflinePlayer(path.getPath(), def);
  }

  /**
   * Checks if the specified path is an OfflinePlayer.
   * <p>
   * If the path exists but is not a OfflinePlayer, this will return false. If
   * the path does not exist, this will return false. If the path does not exist
   * but a default value has been specified, this will check if that default
   * value is a OfflinePlayer and return appropriately.
   *
   * @param path Path of the OfflinePlayer to check.
   *
   * @return Whether or not the specified path is an OfflinePlayer.
   */
  default boolean isOfflinePlayer(final P path) {
    return this.getConfig().isOfflinePlayer(path.getPath());
  }

  /**
   * Gets the requested ItemStack by path.
   * <p>
   * If the ItemStack does not exist but a default value has been specified,
   * this will return the default value. If the ItemStack does not exist and no
   * default value was specified, this will return null.
   *
   * @param path Path of the ItemStack to get.
   *
   * @return Requested ItemStack.
   */
  default ItemStack getItemStack(final P path) {
    return this.getConfig().getItemStack(path.getPath());
  }

  /**
   * Gets the requested {@link ItemStack} by path, returning a default value if
   * not found.
   * <p>
   * If the ItemStack does not exist then the specified default value will
   * returned regardless of if a default has been identified in the root
   * {@link Configuration}.
   *
   * @param path Path of the ItemStack to get.
   * @param def The default value to return if the path is not found or is not
   * an ItemStack.
   *
   * @return Requested ItemStack.
   */
  default ItemStack getItemStack(final P path, final ItemStack def) {
    return this.getConfig().getItemStack(path.getPath(), def);
  }

  /**
   * Checks if the specified path is an ItemStack.
   * <p>
   * If the path exists but is not a ItemStack, this will return false. If the
   * path does not exist, this will return false. If the path does not exist but
   * a default value has been specified, this will check if that default value
   * is a ItemStack and return appropriately.
   *
   * @param path Path of the ItemStack to check.
   *
   * @return Whether or not the specified path is an ItemStack.
   */
  default boolean isItemStack(final P path) {
    return this.getConfig().isItemStack(path.getPath());
  }

  /**
   * Gets the requested Color by path.
   * <p>
   * If the Color does not exist but a default value has been specified, this
   * will return the default value. If the Color does not exist and no default
   * value was specified, this will return null.
   *
   * @param path Path of the Color to get.
   *
   * @return Requested Color.
   */
  default Color getColor(final P path) {
    return this.getConfig().getColor(path.getPath());
  }

  /**
   * Gets the requested {@link Color} by path, returning a default value if not
   * found.
   * <p>
   * If the Color does not exist then the specified default value will returned
   * regardless of if a default has been identified in the root
   * {@link Configuration}.
   *
   * @param path Path of the Color to get.
   * @param def The default value to return if the path is not found or is not a
   * Color.
   *
   * @return Requested Color.
   */
  default Color getColor(final P path, final Color def) {
    return this.getConfig().getColor(path.getPath(), def);
  }

  /**
   * Checks if the specified path is a Color.
   * <p>
   * If the path exists but is not a Color, this will return false. If the path
   * does not exist, this will return false. If the path does not exist but a
   * default value has been specified, this will check if that default value is
   * a Color and return appropriately.
   *
   * @param path Path of the Color to check.
   *
   * @return Whether or not the specified path is a Color.
   */
  default boolean isColor(final P path) {
    return this.getConfig().isColor(path.getPath());
  }

  /**
   * Gets the requested ConfigurationSection by path.
   * <p>
   * If the ConfigurationSection does not exist but a default value has been
   * specified, this will return the default value. If the ConfigurationSection
   * does not exist and no default value was specified, this will return null.
   *
   * @param path Path of the ConfigurationSection to get.
   *
   * @return Requested ConfigurationSection.
   */
  default ConfigurationSection getConfigurationSection(final P path) {
    return this.getConfig().getConfigurationSection(path.getPath());
  }

  /**
   * Checks if the specified path is a ConfigurationSection.
   * <p>
   * If the path exists but is not a ConfigurationSection, this will return
   * false. If the path does not exist, this will return false. If the path does
   * not exist but a default value has been specified, this will check if that
   * default value is a ConfigurationSection and return appropriately.
   *
   * @param path Path of the ConfigurationSection to check.
   *
   * @return Whether or not the specified path is a ConfigurationSection.
   */
  default boolean isConfigurationSection(final P path) {
    return this.getConfig().isConfigurationSection(path.getPath());
  }

  /**
   * Sets the default value in the root at the given path as provided.
   * <p>
   * If no source {@link Configuration} was provided as a default collection,
   * then a new {@link MemoryConfiguration} will be created to hold the new
   * default value.
   * <p>
   * If value is null, the value will be removed from the default Configuration
   * source.
   * <p>
   * If the value as returned by {@link MemorySection#getDefaultSection()} is
   * null, then this will create a new section at the path, replacing anything
   * that may have existed there previously.
   *
   * @param path Path of the value to set.
   * @param value Value to set the default to.
   *
   * @throws IllegalArgumentException Thrown if path is null.
   */
  default void addDefault(final P path, final Object value) {
    this.getConfig().addDefault(path.getPath(), value);
  }
}
