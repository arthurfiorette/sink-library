package com.github.arthurfiorette.sinklibrary.command.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import lombok.*;

@Builder
public class CommandInfo {

  private static String defaultPermissionMessage =
    ChatColor.RED +
    "I'm sorry, but you do not have permission to perform this command. " +
    "Please contact the server administrators if you believe that this is in error.";

  @Getter
  @NonNull
  @Builder.Default
  private String permission = "";

  @Getter
  @NonNull
  @Builder.Default
  private String permissionMessage = defaultPermissionMessage;

  @Getter
  @NonNull
  private String name;

  @Getter
  @NonNull
  @Builder.Default
  private String label = "";

  @Getter
  @NonNull
  private List<String> aliases;

  @Getter
  @NonNull
  @Builder.Default
  private String usage = "";

  @Getter
  @NonNull
  @Builder.Default
  private String description = "";

  public static class CommandInfoBuilder {

    public CommandInfoBuilder() {
      this.aliases = new ArrayList<>();
    }

    // Disable these builder methods
    void subCommands() {}

    void aliases() {}

    public CommandInfoBuilder alias(final String alias) {
      this.aliases.add(alias);
      return this;
    }
  }
}
