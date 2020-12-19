package com.github.hazork.sinkspigot.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import com.github.hazork.sinkspigot.SinkPlugin;
import com.github.hazork.sinkspigot.interfaces.Registrable;

public final class CommandBase implements TabExecutor, Registrable {

    private final String name;
    private final SinkPlugin plugin;

    private Map<String, Argument> commandMap = new HashMap<>();

    private Argument defaultArgument = null;
    private String unknownMessage = "§7Unknown argument.";

    public CommandBase(SinkPlugin plugin, String name) {
	this.plugin = plugin;
	this.name = name;
    }

    @Override
    public void register() {
	plugin.getCommand(name).setExecutor(this);
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
     * @param arg the argument to be set as default, if null remains the same
     *            value.
     * @return the actual default argument.
     */
    public Argument setDefault(Argument arg) {
	return defaultArgument = Objects.isNull(arg) ? defaultArgument : arg;
    }

    public void addArguments(Argument... args) {
	for(Argument arg: args) {
	    commandMap.put(arg.getName(), arg);
	    if (defaultArgument == null) {
		defaultArgument = arg;
	    }
	}
    }

    public void setUnknownMessage(String msg) {
	unknownMessage = msg;
    }

    private static List<String> removeFirst(String[] str) {
	return Arrays.asList(Arrays.copyOfRange(str, 1, str.length));
    }

}
