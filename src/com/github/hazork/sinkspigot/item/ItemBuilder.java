package com.github.hazork.sinkspigot.item;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import com.github.hazork.sinkspigot.services.utils.JavaServices;

public class ItemBuilder {

    private transient ItemStack lastBuild = new ItemStack(Material.AIR);
    private transient boolean modified = true;

    private Material material;
    private EnumMap<Properties, Consumer<ItemStack>> properties = new EnumMap<>(Properties.class);

    public ItemBuilder(Material material) {
	JavaServices.requireNonNull(material);
	this.material = material;
    }

    public ItemBuilder setDurability(int durability) {
	return addProperties(Properties.DAMAGE, is -> is.setDurability((short) durability));
    }

    public ItemBuilder setAmount(int amount) {
	return addProperties(Properties.AMOUNT, is -> is.setAmount(amount));
    }

    public ItemBuilder setData(MaterialData data) {
	return addProperties(Properties.MATERIAL_DATA, is -> is.setData(data));
    }

    public ItemBuilder addEnchantment(Enchantment ench, int level) {
	return addProperties(Properties.ENCHANTMENT, is -> is.addUnsafeEnchantment(ench, level));
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
	return addProperties(Properties.ENCHANTMENT, is -> is.addUnsafeEnchantments(enchantments));
    }

    public ItemBuilder setName(String name) {
	return addProperties(Properties.NAME, is -> setItemMeta(is, im -> im.setDisplayName(name)));
    }

    public ItemBuilder setItemFlags() {
	return setItemFlags(ItemFlag.values());
    }

    public ItemBuilder setItemFlags(ItemFlag... itemFlags) {
	return addProperties(Properties.ITEM_FLAG, is -> setItemMeta(is, im -> {
	    im.removeItemFlags(ItemFlag.values());
	    im.addItemFlags(itemFlags);
	}));
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
	return addProperties(Properties.ITEM_FLAG, is -> setItemMeta(is, im -> im.addItemFlags(itemFlags)));
    }

    public ItemBuilder setLores(String... lorelines) {
	return setLore(Arrays.asList(lorelines));
    }

    public ItemBuilder setLore(List<String> lore) {
	return addProperties(Properties.LORE, is -> setItemMeta(is, im -> im.setLore(lore)));
    }

    public ItemBuilder addLores(String... lorelines) {
	return addLore(Arrays.asList(lorelines));
    }

    public ItemBuilder addLore(List<String> lore) {
	if (properties.containsKey(Properties.LORE)) {
	    return addProperties(Properties.LORE, is -> setItemMeta(is, im -> im.getLore().addAll(lore)));
	}
	return setLore(lore);
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
	return addProperties(Properties.CUSTOM_META,
		is -> setItemMeta(is, im -> im.spigot().setUnbreakable(unbreakable)));
    }

    public ItemBuilder addCustomMeta(UnaryOperator<ItemMeta> customMeta) {
	return addProperties(Properties.CUSTOM_META, is -> is.setItemMeta(customMeta.apply(is.getItemMeta())));
    }

    public ItemStack build() {
	if (!modified) {
	    return getLastBuild();
	}
	ItemStack item = new ItemStack(material);
	properties.values().stream().forEach(c -> c.accept(item));
	return lastBuild = item;
    }

    public ItemStack getLastBuild() {
	return lastBuild;
    }

    public ItemBuilder copy() {
	ItemBuilder clone = new ItemBuilder(material);
	clone.properties = properties;
	return clone;
    }

    public ItemBuilder remove(Properties property) {
	properties.remove(property);
	return this;
    }

    private ItemBuilder addProperties(Properties type, Consumer<ItemStack> consumer) {
	if (properties.containsKey(type) && type.isCumulative()) {
	    properties.compute(type, (k, v) -> v.andThen(consumer));
	} else {
	    properties.put(type, consumer);
	}
	modified = true;
	return this;
    }

    private static void setItemMeta(ItemStack item, Consumer<ItemMeta> callback) {
	ItemMeta meta = item.getItemMeta();
	callback.accept(meta);
	item.setItemMeta(meta);
    }

    public enum Properties {
	NAME(false),
	LORE(false),
	DAMAGE(false),
	AMOUNT(false),
	MATERIAL_DATA(false),
	ENCHANTMENT(true),
	ITEM_FLAG(true),
	CUSTOM_META(true);

	private boolean cumulative;

	Properties(boolean cumulative) {
	    this.cumulative = cumulative;
	}

	public boolean isCumulative() {
	    return cumulative;
	}
    }
}
