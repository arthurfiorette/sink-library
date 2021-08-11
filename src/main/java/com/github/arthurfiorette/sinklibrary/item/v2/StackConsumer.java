package com.github.arthurfiorette.sinklibrary.item.v2;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.NonNull;

@FunctionalInterface
public interface StackConsumer {
  /**
   * @implNote The usage of {@link ItemStack#getItemMeta()} can return a
   * diferent {@link ItemMeta} than the provided argument. But at the end, the
   * provided meta will be applied to the item stack. So, does not use
   * {@link ItemStack#getItemMeta()}.
   *
   * @param item the item stack
   * @param meta the item meta
   */
  void accept(@NonNull ItemStack item, @NonNull ItemMeta meta);

  default StackConsumer andThen(@NonNull final StackConsumer after) {
    return (is, meta) -> {
      this.accept(is, meta);
      after.accept(is, meta);
      is.setItemMeta(meta);
    };
  }
}
