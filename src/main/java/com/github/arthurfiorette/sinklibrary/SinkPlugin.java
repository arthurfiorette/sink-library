package com.github.arthurfiorette.sinklibrary;

import com.github.arthurfiorette.sinklibrary.config.YmlContainer;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class SinkPlugin extends JavaPlugin implements BasePlugin {

  protected final YmlContainer ymlContainer = new YmlContainer(this);
  private final ServiceCoordinator serviceCoordinator = new ServiceCoordinator(this);
  private final Map<Class<? extends BaseComponent>, BaseComponent> components = new HashMap<>();

  protected abstract BaseService[] services();

  protected abstract BaseComponent[] components();

  protected void enable() {}

  protected void disable() {}

  public SinkPlugin() {
    this.serviceCoordinator.add(this.services());
    for (BaseComponent component : this.components()) {
      if (component instanceof BaseService) {
        throw new IllegalArgumentException(
          "You registered an service as an component: " + component.getClass().getSimpleName()
        );
      }
      this.components.put(component.getClass(), component);
    }
  }

  /**
   * @see {@link SinkPlugin#enable()}
   */
  @Override
  public final void onEnable() {
    this.serviceCoordinator.requestEnable();
    this.enable();
  }

  /**
   * @see {@link SinkPlugin#disable()}
   */
  @Override
  public final void onDisable() {
    this.serviceCoordinator.requestDisable();
    this.disable();
  }

  @Override
  public void treatThrowable(Class<?> author, Throwable exc, String message, Object... args) {
    this.log(Level.SEVERE, "An exception occurred in class %s:", author.getSimpleName());
    this.log(Level.SEVERE, message, args);
    exc.printStackTrace();
    if (!(exc instanceof RuntimeException)) {
      // Disable this plugin if this isn't a runtime exception.
      this.log(Level.SEVERE, "Disabling this plugin");
      this.getPluginLoader().disablePlugin(this);
    }
  }

  @Override
  public void register(BaseService... services) {
    this.log(Level.WARNING, "SinkSpigot services must be added with the dedicated method");
    this.serviceCoordinator.add(services);
  }

  @Override
  public boolean unregister(Class<? extends BaseService> clazz, boolean disable) {
    this.log(Level.WARNING, "SinkSpigot services should no");
    return this.serviceCoordinator.remove(clazz, disable);
  }

  @Override
  public <T extends BaseService> T getService(Class<T> clazz) {
    return this.serviceCoordinator.getService(clazz);
  }

  @Override
  public <T extends BaseComponent> T getComponent(Class<T> clazz) {
    return clazz.cast(this.components.get(clazz));
  }
}
