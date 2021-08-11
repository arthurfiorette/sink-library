package com.github.arthurfiorette.sinklibrary.item.v2;

import java.util.EnumMap;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.NonNull;

public final class PropertiesMap extends EnumMap<ItemProperty, StackConsumer> {

  private static final long serialVersionUID = 3984485171955149718L;

  public PropertiesMap() {
    super(ItemProperty.class);
  }

  @Override
  public StackConsumer put(@NonNull final ItemProperty key, @NonNull StackConsumer value) {
    final StackConsumer consumer = this.get(key);

    // Merge these values if the key is cumulative and already has one stack
    // consumer
    if (consumer != null && key.isCumulative()) {
      value = consumer.andThen(value);
    }

    return super.put(key, value);
  }

  @Override
  public PropertiesMap clone() {
    return (PropertiesMap) super.clone();
  }

  public void apply(final ItemStack item) {
    final ItemMeta meta = item.getItemMeta();
    for (final StackConsumer consumer : this.values()) {
      consumer.accept(item, meta);
    }
    item.setItemMeta(meta);
  }
}
