package com.github.arthurfiorette.sinklibrary.item.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ItemProperty {
  
  AMOUNT(false),
  CUSTOM_META(true),
  DAMAGE(false),
  ENCHANTMENT(true),
  ITEM_FLAG(true),
  LORE(false),
  MATERIAL(false),
  MATERIAL_DATA(false),
  NAME(false),
  UNBREAKABLE(false);

  /**
   * Return true if it can exists many of this properties in a single {@link ItemBuilder}
   */
  @Getter
  private boolean cumulative;
}

