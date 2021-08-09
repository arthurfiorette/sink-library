package com.github.arthurfiorette.sinklibrary.item;

/**
 * @deprecated in flavor of
 * {@link com.github.arthurfiorette.sinklibrary.item.v2.ItemProperty}
 */
@Deprecated
public enum ItemProperty {
  NAME(false),
  LORE(false),
  DAMAGE(false),
  AMOUNT(false),
  MATERIAL_DATA(false),
  ENCHANTMENT(true),
  ITEM_FLAG(true),
  CUSTOM_META(true);

  private boolean cumulative;

  ItemProperty(final boolean cumulative) {
    this.cumulative = cumulative;
  }

  public boolean isCumulative() {
    return this.cumulative;
  }
}
