package com.github.arthurfiorette.sinklibrary.command.wrapper;

import com.github.arthurfiorette.sinklibrary.command.BaseCommand;
import com.github.arthurfiorette.sinklibrary.command.CommandUtils;
import com.github.arthurfiorette.sinklibrary.tuple.Pair;
import com.github.arthurfiorette.sinklibrary.tuple.ValidPair;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandWrapper extends Command {

  @Getter
  @NonNull
  private final BaseCommand command;

  @Getter
  @NonNull
  private final CommandWrapper[] subCommands;

  @Getter
  @NonNull
  private final CommandInfo info;

  public CommandWrapper(final BaseCommand command, final CommandInfo info) {
    super(info.getName(), info.getDescription(), info.getUsage(), info.getAliases());
    super.setLabel(info.getLabel());
    super.setPermission(info.getPermission());
    super.setPermissionMessage(info.getPermissionMessage());
    this.command = command;
    this.info = info;
    this.subCommands = CommandUtils.wrapAll(command.subCommands());
  }

  /**
   * this method always returns {@code true}, because if the boolean returned by
   * it is false, the command usage message is sent.
   * <p>
   * This method is only called by bukkit, for any subcommand execution, method
   * X is called directly.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public boolean execute(
    final CommandSender sender,
    final String nameOrAliasUsed,
    final String[] args
  ) {
    if (!this.testPermission(sender)) {
      return true;
    }

    final Pair<CommandWrapper, List<String>> pair = this.findHandlerRecursively(args);

    try {
      pair.getLeft().command.handle(sender, pair.getRight());
    } catch (final Throwable e) {
      sender.sendMessage("§cThis command had some problems, we are sorry for the inconvenience...");
    }

    return true;
  }

  @Override
  public List<String> tabComplete(
    final CommandSender sender,
    final String alias,
    final String[] args
  ) throws IllegalArgumentException {
    final Pair<CommandWrapper, List<String>> pair = this.findHandlerRecursively(args);
    return pair.getLeft().command.onTabComplete(sender, pair.getRight());
  }

  @Override
  public boolean testPermission(final CommandSender target) {
    if (this.testPermissionSilent(target) && this.command.test(target)) {
      return true;
    }

    if (this.getPermissionMessage().length() != 0) {
      for (final String line : this.getPermissionMessage()
        .replace("<permission>", this.getPermission())
        .split("\n")) {
        target.sendMessage(line);
      }
    }

    return false;
  }

  private boolean canHandle(final String nameOrAlias) {
    return this.info.getName().equals(nameOrAlias) || this.info.getAliases().contains(nameOrAlias);
  }

  /**
   * @param args the initial argument list.
   *
   * @return a pair containing the wrapper found and the argument list that
   * should be used.
   */
  private Pair<CommandWrapper, List<String>> findHandlerRecursively(final String[] args) {
    // Começa a buscar por handlers dos argumentos, e caso não for encontrado,
    // continue sendo esta instancia
    CommandWrapper handler = this;
    final List<String> argList = Lists.newArrayList(args);

    // Começa a procura recusiva para handlers dos argumentos, e caso não for
    // encontrado,
    // continua sendo esta instancia
    for (final String arg : args) {
      for (final CommandWrapper wrapper : handler.subCommands) {
        if (wrapper.canHandle(arg)) {
          // Argumento encontrado!, Remova o nome da lista de argumentos. Pare
          // somente este primeiro for e continue a buscar por subhandlers deste
          // handler para os próximos argumentos.

          handler = wrapper;
          argList.remove(0);
          break;
        }
      }

      // A lista de handlers foi passada e nenhum handler foi encontrado para
      // este argumento, deixe o handler selecionado como handler final
      break;
    }

    return new ValidPair<>(handler, argList);
  }
}
