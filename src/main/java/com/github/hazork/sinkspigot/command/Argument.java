package com.github.hazork.sinkspigot.command;

import java.util.List;
import java.util.function.Predicate;

import org.bukkit.command.CommandSender;

/**
 * An argument is a interface build specific for handling arguments for a
 * command. Create a command in a commandBase and add any arguments that you
 * need. {@link CommandBase}.
 *
 * @author https://github.com/Hazork/sink-library/
 */
public interface Argument extends Predicate<CommandSender> {

    /**
     * Handle a argument executed from a command sender.
     *
     * @param sender the command sender that executed this argument
     * @param alias the next aliases
     * @param args the next arguments
     */
    void onArgument(CommandSender sender, String alias, List<String> args);

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
     * @param args
     * 
     * @return the next arguments or {@code null} to be none.
     */
    default List<String> onTabComplete(CommandSender sender, String alias, List<String> args) {
	return null;
    }
}