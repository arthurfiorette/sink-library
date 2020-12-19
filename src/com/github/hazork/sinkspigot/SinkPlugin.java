package com.github.hazork.sinkspigot;

import java.util.Arrays;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.hazork.sinkspigot.command.CommandBase;
import com.github.hazork.sinkspigot.config.YMLContainer;
import com.github.hazork.sinkspigot.interfaces.Registrable;

public abstract class SinkPlugin extends JavaPlugin {

    final YMLContainer ymlContainer = new YMLContainer(this);

    public SinkPlugin() {
	Sinks.newInstance(this);
    }

    @Override
    public abstract void onEnable();

    @Override
    public abstract void onDisable();

    public void treatException(Class<?> author, Exception e, String message, Object... args) {
	log(Level.SEVERE, "An exception occurred in class %s:", author.getSimpleName());
	log(Level.SEVERE, message, args);
	e.printStackTrace();
	getPluginLoader().disablePlugin(this);
    }

    public void log(Level level, String msg, Object... args) {
	getLogger().log(level, String.format(msg, args));
    }

    public void addRegistrables(Registrable... registries) {
	Arrays.stream(registries).forEach(Registrable::register);
    }

    public void addCommandBase(CommandBase... commands) {
	for(CommandBase cb: commands) {
	    cb.register();
	}
    }

    public void addListeners(Listener... listeners) {
	Arrays.stream(listeners).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    public SinkPlugin getPlugin() {
	return this;
    }

}
