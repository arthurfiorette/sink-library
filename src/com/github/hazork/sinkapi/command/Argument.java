package com.github.hazork.sinkapi.command;

import java.util.List;
import java.util.function.Predicate;

import org.bukkit.command.CommandSender;

public interface Argument extends Predicate<CommandSender> {

    void onArgument(CommandSender sender, String alias, List<String> args);

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
     * @return the next arguments or {@code null} to be none.
     */
    default List<String> onTabComplete(CommandSender sender, String alias, List<String> args) {
	return null;
    }
}