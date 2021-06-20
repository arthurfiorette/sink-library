package com.github.arthurfiorette.sinklibrary.item;

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

  ItemProperty(boolean cumulative) {
    this.cumulative = cumulative;
  }

  public boolean isCumulative() {
    return this.cumulative;
  }
}
