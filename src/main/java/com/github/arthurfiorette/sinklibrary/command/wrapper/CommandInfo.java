package com.github.arthurfiorette.sinklibrary.command.wrapper;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import org.bukkit.ChatColor;

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
  @Singular
  private List<String> aliases;

  @Getter
  @NonNull
  @Builder.Default
  private String usage = "";

  @Getter
  @NonNull
  @Builder.Default
  private String description = "";
}
