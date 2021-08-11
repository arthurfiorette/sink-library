package com.github.arthurfiorette.sinklibrary.menu.item;

import com.github.arthurfiorette.sinklibrary.interfaces.Builder;
import com.github.arthurfiorette.sinklibrary.menu.BaseMenu;
import com.github.arthurfiorette.sinklibrary.menu.listener.ClickListener;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class BuilderStack implements MenuItem {

  /**
   * The inventory will only update when the next {@link BaseMenu#update()} be
   * called
   *
   * @param builder the item builder
   */
  @Setter
  @Getter
  @NonNull
  private Builder<ItemStack> builder;

  @Getter
  @Setter
  @NonNull
  private ClickListener listener;

  public BuilderStack(final Builder<ItemStack> builder) {
    this(builder, ClickListener.ignore());
  }

  @Override
  public ItemStack getItem() {
    return this.builder.build();
  }
}
