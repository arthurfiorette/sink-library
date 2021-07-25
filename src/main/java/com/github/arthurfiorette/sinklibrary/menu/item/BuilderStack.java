package com.github.arthurfiorette.sinklibrary.menu.item;

import org.bukkit.inventory.ItemStack;

import com.github.arthurfiorette.sinklibrary.item.ItemBuilder;
import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;

import lombok.*;

@RequiredArgsConstructor
public class BuilderStack implements MenuItem {

  @Getter
  @NonNull
  private ItemBuilder builder;

  @Getter
  @Setter
  @NonNull
  private ClickListener listener;

  public BuilderStack(final ItemBuilder builder) {
    this(builder, ClickListener.ignore());
  }

  @Override
  public ItemStack getItem() {
    return this.builder.build();
  }

  /**
   * The inventory will only update when the next {@link BaseMenu#update()} be
   * called
   *
   * @param builder the item builder
   */
  public void setBuilder(final ItemBuilder builder) {
    this.builder = builder;
  }
}
