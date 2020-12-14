package com.github.hazork.sinkspigot;

import java.util.Arrays;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.hazork.sinkspigot.command.Argument;
import com.github.hazork.sinkspigot.command.CommandBase;
import com.github.hazork.sinkspigot.interfaces.Registrable;
import com.github.hazork.sinkspigot.scheduler.SinkScheduler;

public abstract class SinkPlugin extends JavaPlugin implements SinkObject {

    final SinkScheduler scheduler = new SinkScheduler(this);

    public SinkPlugin() {
	Sinks.newInstance(this);
    }

    @Override
    public abstract void onEnable();

    @Override
    public abstract void onDisable();

    public void log(Level level, String msg) {
	getLogger().log(level, msg);
    }

    public void addRegistrables(Registrable... registries) {
	Arrays.stream(registries).forEach(Registrable::register);
    }

    public void addCommandBase(CommandBase command, Argument defaultArg, Argument... arguments) {
	command.setDefault(defaultArg);
	addCommandBase(command, arguments);
    }

    public void addCommandBase(CommandBase command, Argument... arguments) {
	command.addArguments(arguments);
	command.register();
    }

    public void addListeners(Listener... listeners) {
	Arrays.stream(listeners).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    @Override
    public SinkPlugin getPlugin() {
	return this;
    }
}
