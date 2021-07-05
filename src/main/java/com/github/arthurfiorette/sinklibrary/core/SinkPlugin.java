package com.github.arthurfiorette.sinklibrary.core;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SinkPlugin extends JavaPlugin implements BasePlugin, BaseService {

  @Getter
  @NonNull
  private final ComponentManager manager;

  public SinkPlugin() {
    this.manager = new SimpleComponentManager(this);
  }

  /**
   * @return the component array to register all of yours components and
   * services
   */
  protected abstract BaseComponent[] components();

  /**
   * @see SinkPlugin#enable()
   */
  @Override
  public final void onEnable() {
    this.manager.enableServices();
  }

  /**
   * @see SinkPlugin#disable()
   */
  @Override
  public final void onDisable() {
    this.manager.disableServices();
  }

  @Override
  public void treatThrowable(final Class<?> author, final Throwable exc, final String message, final Object... args) {
    this.log(Level.SEVERE, "An exception occurred in class %s.", author.getSimpleName());
    this.log(Level.SEVERE, message, args);
    exc.printStackTrace();
    if (!(exc instanceof RuntimeException)) {
      // Disable this plugin if this isn't a runtime exception.
      this.log(Level.SEVERE, "Disabling this plugin");
      this.getPluginLoader().disablePlugin(this);
    }
  }

  @Override
  public BasePlugin getBasePlugin() {
    return this;
  }
}
