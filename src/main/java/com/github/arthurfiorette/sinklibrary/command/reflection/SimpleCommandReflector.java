package com.github.arthurfiorette.sinklibrary.command.reflection;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import java.lang.reflect.Field;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;

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
  public void run() {
    try {
      pluginManager = getBasePlugin().getServer().getPluginManager();
      final Field field = getCommandMapField();
      commandMap = (CommandMap) field.get(pluginManager);
    } catch (final NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
      throw new RuntimeException(
        SimpleCommandReflector.EXC_TITLE + " maybe you are using an incompatible version?",
        e
      );
    } catch (final SecurityException e) {
      throw new RuntimeException(
        SimpleCommandReflector.EXC_TITLE + " do we have permission to do this?",
        e
      );
    }
  }

  private Field getCommandMapField() throws NoSuchFieldException, SecurityException {
    final Field field =
      pluginManager.getClass().getDeclaredField(SimpleCommandReflector.COMMAND_MAP_FIELD);
    field.setAccessible(true);
    return field;
  }
}
