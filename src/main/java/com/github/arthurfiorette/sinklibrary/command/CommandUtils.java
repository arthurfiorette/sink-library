package com.github.arthurfiorette.sinklibrary.command;

import com.github.arthurfiorette.sinklibrary.command.wrapper.CommandInfo;
import com.github.arthurfiorette.sinklibrary.command.wrapper.CommandInfo.CommandInfoBuilder;
import com.github.arthurfiorette.sinklibrary.command.wrapper.CommandWrapper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommandUtils {

  public CommandWrapper[] wrapAll(final BaseCommand[] commands) {
    final CommandWrapper[] wrapped = new CommandWrapper[commands.length];

    for (int i = 0; i < commands.length; i++) {
      wrapped[i] = CommandUtils.wrap(commands[i]);
    }

    return wrapped;
  }

  public CommandWrapper wrap(final BaseCommand command) {
    final CommandInfoBuilder builder = CommandInfo.builder();
    command.info(builder);
    final CommandWrapper wrapper = new CommandWrapper(command, builder.build());
    return wrapper;
  }
}
