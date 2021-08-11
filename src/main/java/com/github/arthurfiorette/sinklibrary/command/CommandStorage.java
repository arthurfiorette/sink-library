package com.github.arthurfiorette.sinklibrary.command;

import com.github.arthurfiorette.sinklibrary.command.reflection.CommandReflector;
import com.github.arthurfiorette.sinklibrary.command.reflection.SimpleCommandReflector;
import com.github.arthurfiorette.sinklibrary.component.Service;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;

import org.bukkit.command.CommandMap;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CommandStorage implements Service {

  @Getter
  @NonNull
  protected final BasePlugin basePlugin;

  @Getter
  @NonNull
  protected final CommandReflector commandReflector;

  public CommandStorage(final BasePlugin basePlugin) {
    this.basePlugin = basePlugin;
    this.commandReflector = new SimpleCommandReflector(basePlugin);
  }

  protected abstract BaseCommand[] commands();

  @Override
  public void enable()  {
    this.commandReflector.run();
    final CommandMap commandMap = this.commandReflector.getCommandMap();

    for (final BaseCommand command : this.commands()) {
      final String prefix = this.getBasePlugin().getName();
      commandMap.register(prefix, CommandUtils.wrap(command));
    }
  }

  @Override
  public void disable()  {}
}
