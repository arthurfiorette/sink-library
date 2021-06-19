package br.com.arthurfiorette.sinklibrary;

import java.util.Arrays;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.arthurfiorette.sinklibrary.command.CommandBase;
import br.com.arthurfiorette.sinklibrary.config.YmlContainer;
import br.com.arthurfiorette.sinklibrary.config.YmlFile;
import br.com.arthurfiorette.sinklibrary.interfaces.Registrable;
import br.com.arthurfiorette.sinklibrary.listener.SinkListener;
import br.com.arthurfiorette.sinklibrary.menu.listener.MenuListener;

/**
 * A JavaPlugin with the compatibility of several library methods
 *
 * @author https://github.com/Hazork/sink-library/
 */
public abstract class SinkPlugin extends JavaPlugin {

  private final YmlContainer ymlContainer = new YmlContainer(this);
  private MenuListener listener;

  /**
   * Creates a new SinkPluign. You should be aware that only one per class can
   * be instantiated and only with
   * {@link org.bukkit.plugin.java.JavaPluginLoader}.
   */
  public SinkPlugin() {}

  @Override
  public abstract void onEnable();

  @Override
  public abstract void onDisable();

  /**
   * Handles exceptions in a better visual way
   *
   * @param author the class author
   * @param exc the exception
   * @param message the message
   * @param args the arguments to replace the message.
   * {@link String#format(String, Object...)}
   */
  public void treatException(Class<?> author, Exception exc, String message, Object... args) {
    this.log(Level.SEVERE, "An exception occurred in class %s:", author.getSimpleName());
    this.log(Level.SEVERE, message, args);
    exc.printStackTrace();
    this.getPluginLoader().disablePlugin(this);
  }

  /**
   * Log any information with the plugin tag and etc. Best replacement for
   * System.out.println()
   *
   * @param level the log level.
   * @param msg any information that needs to be logged
   * @param args the arguments to replace the message.
   * {@link String#format(String, Object...)}
   */
  public void log(Level level, String msg, Object... args) {
    this.getLogger().log(level, String.format(msg, args));
  }

  /**
   * Register any registrable.
   *
   * @param registries the registries
   */
  public void addRegistrables(Registrable... registries) {
    Arrays.stream(registries).forEach(Registrable::register);
  }

  /**
   * Same as {@link SinkPlugin#addRegistrables(Registrable...)}
   *
   * @param commands the commands to register
   */
  @Deprecated
  public void addCommandBase(CommandBase... commands) {
    for (CommandBase cb : commands) {
      cb.register();
    }
  }

  /**
   * You should use {@link SinkListener} for better visibility and for being
   * registered with {@link SinkListener#register()}
   *
   * @param listeners the listeners to register
   */
  @Deprecated
  public void addListeners(Listener... listeners) {
    Arrays
      .stream(listeners)
      .forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
  }

  /**
   * Add any YmlFile with this plugin.
   *
   * @param files the files to be added.
   */
  protected void addFile(YmlFile... files) {
    Arrays.stream(files).forEach(file -> ymlContainer.addFile(file));
  }

  /**
   * Return the YmlContainer connected with this plugin
   *
   * @return the container
   */
  public YmlContainer getYmlContainer() {
    return ymlContainer;
  }

  /**
   * This is auto executed when a menu from this plugin is created so all their
   * listeners work well
   */
  public void setupMenus() {
    if (listener == null) {
      listener = new MenuListener(this);
      listener.register();
    }
  }
}
