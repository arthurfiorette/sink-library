package com.github.arthurfiorette.sinklibrary.command;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.core.SinkPlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;

/**
 * This class is a way to create commands
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public final class CommandBase implements TabExecutor, BaseService {

  private final String name;
  private final SinkPlugin plugin;

  private final Map<String, Argument> commandMap = new HashMap<>();

  private Argument defaultArgument = null;
  private String unknownMessage = "§7Unknown argument.";

  /**
   * Constructs a command base
   *
   * @param plugin the sink plugin instance
   * @param name the command name
   */
  public CommandBase(final SinkPlugin plugin, final String name) {
    this.plugin = plugin;
    this.name = name;
  }

  /**
   * {@inheritDoc}
   *
   * @throws VerifyException if the command isn't found. (ensure that it is
   * registered in plugin.yml}
   */
  @Override
  public void enable() {
    final PluginCommand command = this.plugin.getCommand(this.name);
    Verify.verifyNotNull(command, "The command %s wasn't found", this.name);
    command.setExecutor(this);
    command.setTabCompleter(this);
  }

  @Override
  public void disable() {
    final PluginCommand command = this.plugin.getCommand(this.name);
    command.setExecutor(null);
    command.setTabCompleter(null);
  }

  @Override
  public boolean onCommand(
    final CommandSender sender,
    final Command command,
    final String alias,
    final String[] args
  ) {
    if (args.length == 0) {
      this.defaultArgument.handle(sender, alias, Arrays.asList(args));
      return true;
    }
    final Argument cmd = this.commandMap.get(args[0]);
    if ((cmd != null) && cmd.test(sender)) {
      if ((cmd.getPermission() != null) && sender.hasPermission(cmd.getPermission())) {
        cmd.handle(sender, alias, CommandBase.removeFirst(args));
      } else {
        sender.sendMessage(cmd.getPermissionMessage());
      }
    } else {
      sender.sendMessage(this.unknownMessage);
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(
    final CommandSender sender,
    final Command command,
    final String alias,
    final String[] args
  ) {
    if (args.length == 0) {
      return new ArrayList<>(this.commandMap.keySet());
    } else if (this.commandMap.containsKey(args[0])) {
      return this.commandMap.get(args[0])
        .onTabComplete(sender, alias, CommandBase.removeFirst(args));
    } else {
      return null;
    }
  }

  /**
   * Set an argument as a default command, (when no args are specified)
   *
   * @param arg the argument to be set or null to be none.
   *
   * @return the default argument.
   */
  public Argument setDefault(final Argument arg) {
    return this.defaultArgument = arg == null ? this.defaultArgument : arg;
  }

  /**
   * @param args all the new arguments to be added to this command
   */
  public void addArguments(final Argument... args) {
    for (final Argument arg : args) {
      this.commandMap.put(arg.getName(), arg);
      if (this.defaultArgument == null) {
        this.defaultArgument = arg;
      }
    }
  }

  /**
   * @param msg change the default unknown message.
   */
  public void setUnknownMessage(final String msg) {
    this.unknownMessage = msg;
  }

  private static List<String> removeFirst(final String[] str) {
    return Arrays.asList(Arrays.copyOfRange(str, 1, str.length));
  }

  @Override
  public BasePlugin getBasePlugin() {
    return this.plugin;
  }
}
