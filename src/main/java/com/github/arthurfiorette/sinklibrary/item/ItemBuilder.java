package com.github.arthurfiorette.sinklibrary.item;

import com.github.arthurfiorette.sinklibrary.interfaces.Builder;
import com.github.arthurfiorette.sinklibrary.menu.item.BuilderStack;
import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

@RequiredArgsConstructor
public class ItemBuilder implements Builder<ItemStack> {

  /**
   * Returns the last item build or null if not found.
   */
  @Getter
  private transient ItemStack lastBuild = null;

  /**
   * Returns true if this build item was modified. Meaning that a new call to
   * {@link #build()} will be diferrent
   */
  @Getter
  private transient boolean modified = true;

  @Getter
  @NonNull
  private Material material;

  @Getter
  private PropertiesMap propertiesMap = new PropertiesMap();

  /**
   * @param durability set item durability
   *
   * @return itself
   */
  public ItemBuilder material(final Material material) {
    return addProperties(
      ItemProperty.MATERIAL,
      (is, meta) -> {
        is.setType(material);
      }
    );
  }

  /**
   * @param durability set item durability
   *
   * @return itself
   */
  public ItemBuilder durability(final short durability) {
    return addProperties(
      ItemProperty.DAMAGE,
      (is, meta) -> {
        is.setDurability(durability);
      }
    );
  }

  /**
   * @param amount set the item amount
   *
   * @return itself
   */
  public ItemBuilder amount(final int amount) {
    return addProperties(
      ItemProperty.AMOUNT,
      (is, meta) -> {
        is.setAmount(amount);
      }
    );
  }

  /**
   * @param data set the item data
   *
   * @return itself
   */
  public ItemBuilder data(final MaterialData data) {
    return addProperties(
      ItemProperty.MATERIAL_DATA,
      (is, meta) -> {
        is.setData(data);
      }
    );
  }

  /**
   * @param ench the enchantment to add
   * @param level the enchantment level
   *
   * @return itself
   */
  public ItemBuilder enchantment(final Enchantment ench, final int level) {
    return addProperties(
      ItemProperty.ENCHANTMENT,
      (is, meta) -> {
        is.addUnsafeEnchantment(ench, level);
      }
    );
  }

  /**
   * @param enchantments set the item enchantments in a map of
   * {@code <enchantment,
   *                     level>}
   *
   * @return itself
   */
  public ItemBuilder enchantments(final Map<Enchantment, Integer> enchantments) {
    return addProperties(
      ItemProperty.ENCHANTMENT,
      (is, meta) -> {
        is.addUnsafeEnchantments(enchantments);
      }
    );
  }

  /**
   * @param name set the item name
   *
   * @return itself
   */
  public ItemBuilder name(final String name) {
    return addProperties(
      ItemProperty.NAME,
      (is, meta) -> {
        meta.setDisplayName(name);
      }
    );
  }

  /**
   * Add to the item all the item flags.
   *
   * @return itself
   */
  public ItemBuilder allItemFlags() {
    return itemFlags(ItemFlag.values());
  }

  /**
   * @param itemFlags set to the item all the item flags specified in varargs
   *
   * @return itself
   */
  public ItemBuilder itemFlags(final ItemFlag... itemFlags) {
    return addProperties(
      ItemProperty.ITEM_FLAG,
      (is, meta) -> {
        meta.addItemFlags(itemFlags);
      }
    );
  }

  public ItemBuilder lores(final String... lines) {
    return this.lores(Arrays.asList(lines));
  }

  public ItemBuilder lores(final List<String> lines) {
    return addProperties(
      ItemProperty.LORE,
      (is, meta) -> {
        final List<String> lore = meta.getLore() == null ? new ArrayList<>() : meta.getLore();
        lore.addAll(lines);
        meta.setLore(lore);
      }
    );
  }

  /**
   * @param unbreakable true if the items needs to be unbreakable
   *
   * @return itself
   */
  public ItemBuilder unbreakable(final boolean unbreakable) {
    return addProperties(
      ItemProperty.UNBREAKABLE,
      (is, meta) -> {
        meta.spigot().setUnbreakable(unbreakable);
      }
    );
  }

  /**
   * @param customMeta any custom meta to be added to the item at the end
   *
   * @return itself
   */
  public ItemBuilder apply(final StackConsumer consumer) {
    return addProperties(ItemProperty.CUSTOM_META, consumer);
  }

  /**
   * Build a stack of items with this real configuration. if nothing has been
   * changed, it will return the last saved construction.
   *
   * @return the item stack
   */
  @Override
  public ItemStack build() {
    if (!modified) {
      return lastBuild;
    }

    final ItemStack item = new ItemStack(material);
    propertiesMap.apply(item);

    modified = false;
    return lastBuild = item;
  }

  public BuilderStack asMenuItem() {
    return new BuilderStack(this);
  }

  public BuilderStack asMenuItem(final ClickListener listener) {
    return new BuilderStack(this, listener);
  }

  /**
   * @return a copy fro this builder
   */
  public ItemBuilder copy() {
    final ItemBuilder clone = new ItemBuilder(material);
    clone.propertiesMap = propertiesMap.clone();
    clone.lastBuild = lastBuild;
    clone.modified = modified;
    return clone;
  }

  /**
   * Remove any property from this builder
   *
   * @param property the property to remove
   *
   * @return itself
   */
  public ItemBuilder clear(final ItemProperty property) {
    propertiesMap.remove(property);
    modified = true;
    return this;
  }

  private ItemBuilder addProperties(final ItemProperty type, final StackConsumer consumer) {
    propertiesMap.put(type, consumer);
    modified = true;
    return this;
  }
}
