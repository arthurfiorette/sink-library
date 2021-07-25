package com.github.arthurfiorette.sinklibrary.command.reflection;

import java.lang.reflect.Field;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.exceptions.EnablingException;

import lombok.Getter;
import lombok.NonNull;

public class SimpleCommandReflector implements CommandReflector {

  private static final String COMMAND_MAP_FIELD = "commandMap";

  private static final String EXC_TITLE =
    "Field not found while accessing the SimpleCommandMap class,";

  @Getter
  @NonNull
  private final BasePlugin basePlugin;

  @Getter
  @NonNull
  private CommandMap commandMap;

  @Getter
  @NonNull
  private PluginManager pluginManager;

  public SimpleCommandReflector(final BasePlugin basePlugin) {
    this.basePlugin = basePlugin;
  }

  @Override
  public void enable() {
    try {
      this.pluginManager = this.getBasePlugin().getServer().getPluginManager();
      final Field field = this.getCommandMapField();
      this.commandMap = (CommandMap) field.get(this.pluginManager);
    } catch (final NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
      throw new EnablingException(
        SimpleCommandReflector.EXC_TITLE + " maybe you are using an incompatible version?",
        e
      );
    } catch (final SecurityException e) {
      throw new EnablingException(
        SimpleCommandReflector.EXC_TITLE + " do we have permission to do this?",
        e
      );
    }
  }

  private Field getCommandMapField() throws NoSuchFieldException, SecurityException {
    final Field field =
      this.pluginManager.getClass().getDeclaredField(SimpleCommandReflector.COMMAND_MAP_FIELD);
    field.setAccessible(true);
    return field;
  }
}
