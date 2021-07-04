package com.github.arthurfiorette.sinklibrary.command;

import java.util.List;
import java.util.function.Predicate;

import org.bukkit.command.CommandSender;

/**
 * An argument is a interface build specific for handling arguments for a
 * command. Create a command in a commandBase and add any arguments that you
 * need. {@link CommandBase}.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public interface Argument extends Predicate<CommandSender> {
  /**
   * Handle a argument executed from a command sender.
   *
   * @param sender the command sender that executed this argument
   * @param alias the alias used
   * @param args the next arguments
   */
  void handle(CommandSender sender, String alias, List<String> args);

  /**
   * @return this argument name
   */
  String getName();

  /**
   * @return the permission or {@code null} to be ignored.
   */
  default String getPermission() {
    return null;
  }

  /**
   * @return the permission message or {@code null} to be default.
   */
  default String getPermissionMessage() {
    return null;
  }

  /**
   * Handle a tab complete request from a command sender
   *
   * @param sender the command sender
   * @param alias the next aliases
   * @param args any argument that the sender should provide
   *
   * @return the next arguments or {@code null} to be none.
   */
  default List<String> onTabComplete(
    final CommandSender sender,
    final String alias,
    final List<String> args
  ) {
    return null;
  }
}
