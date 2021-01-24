package com.github.hazork.sinkspigot.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;

import com.github.hazork.sinkspigot.SinkPlugin;
import com.github.hazork.sinkspigot.interfaces.Registrable;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;

/**
 * This class is a way to create commands
 *
 * @author https://github.com/Hazork/sink-library/
 */
public final class CommandBase implements TabExecutor, Registrable {

    private final String name;
    private final SinkPlugin plugin;

    private Map<String, Argument> commandMap = new HashMap<>();

    private Argument defaultArgument = null;
    private String unknownMessage = "§7Unknown argument.";

    /**
     * Constructs a command base
     *
     * @param plugin the sink plugin instance
     * @param name the command name
     */
    public CommandBase(SinkPlugin plugin, String name) {
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
    public void register() {
	PluginCommand command = plugin.getCommand(name);
	Verify.verifyNotNull(command, "The command %s wasn't found", name);
	command.setExecutor(this);
	command.setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
	if (args.length == 0) {
	    defaultArgument.onArgument(sender, alias, Arrays.asList(args));
	    return true;
	}
	Argument cmd = commandMap.get(args[0]);
	if (cmd != null && cmd.test(sender)) {
	    if (cmd.getPermission() != null && sender.hasPermission(cmd.getPermission())) {
		cmd.onArgument(sender, alias, removeFirst(args));
	    } else {
		sender.sendMessage(cmd.getPermissionMessage());
	    }
	} else {
	    sender.sendMessage(unknownMessage);
	}
	return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
	if (args.length == 0) {
	    return new ArrayList<>(commandMap.keySet());
	} else if (commandMap.containsKey(args[0])) {
	    return commandMap.get(args[0]).onTabComplete(sender, alias, removeFirst(args));
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
    public Argument setDefault(Argument arg) {
	return defaultArgument = Objects.isNull(arg) ? defaultArgument : arg;
    }

    /**
     * @param args all the new arguments to be added to this command
     */
    public void addArguments(Argument... args) {
	for(Argument arg: args) {
	    commandMap.put(arg.getName(), arg);
	    if (defaultArgument == null) {
		defaultArgument = arg;
	    }
	}
    }

    /**
     * @param msg change the default unknown message.
     */
    public void setUnknownMessage(String msg) {
	unknownMessage = msg;
    }

    private static List<String> removeFirst(String[] str) {
	return Arrays.asList(Arrays.copyOfRange(str, 1, str.length));
    }

}
