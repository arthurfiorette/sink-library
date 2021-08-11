package com.github.arthurfiorette.sinklibrary.command;

import com.github.arthurfiorette.sinklibrary.command.wrapper.CommandInfo.CommandInfoBuilder;
import com.github.arthurfiorette.sinklibrary.component.Component;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;

import java.util.Collection;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;

public interface BaseCommand extends Component, PluginIdentifiableCommand {
  
  void handle(final CommandSender sender, final Collection<String> args);

  List<String> onTabComplete(final CommandSender sender, final Collection<String> args);

  /**
   * Use this methods to apply any information about this command;
   *
   * @param info the command info
   */
  void info(final CommandInfoBuilder info);

  /**
   * This method is used before the handling step, so we can know what entity
   * can use this command.
   * <p>
   * This must return true for all entities that <b>with permission</b> can use
   * this command.
   *
   * @param sender the sender to test
   *
   * @return true if it can use this command
   */
  boolean test(final CommandSender sender);

  /**
   * The same as {@link #getBasePlugin()}
   */
  @Override
  default BasePlugin getPlugin() {
    return this.getBasePlugin();
  }

  default BaseCommand[] subCommands() {
    return new BaseCommand[0];
  }
}
