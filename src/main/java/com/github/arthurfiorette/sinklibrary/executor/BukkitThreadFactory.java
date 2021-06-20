package com.github.arthurfiorette.sinklibrary.executor;

import java.util.concurrent.ThreadFactory;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.plugin.BasePlugin;

/**
 * Every created thread from this object runs with the specified
 * {@link TaskContext}.
 * <p>
 * An object that creates new threads on demand. Using thread factories removes
 * hardwiring of calls to {@link Thread#Thread(Runnable) new Thread}, enabling
 * applications to use special thread subclasses, priorities, etc.
 * <p>
 * The {@link BukkitExecutor#asyncThreadFactory(BasePlugin)} or
 * {@link BukkitExecutor#syncThreadFactory(BasePlugin)} method provides a more
 * useful simple implementation, that sets the created thread context to known
 * values before returning it.
 * 
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public class BukkitThreadFactory implements ThreadFactory, BaseComponent {

  private BasePlugin plugin;
  private TaskContext context;

  public BukkitThreadFactory(BasePlugin plugin, TaskContext context) {
    this.plugin = plugin;
    this.context = context;
  }

  /**
   * Create a async thread factory that every thread runs in asynchronously with
   * {@link BukkitScheduler#runTaskAsynchronously(Plugin, Runnable)}.
   * 
   * @param plugin The basic plugin to assign to the created thread factory
   * 
   * @return the newly creted ThreadFactory
   */
  public static ThreadFactory ofAsync(BasePlugin plugin) {
    return new BukkitThreadFactory(plugin, TaskContext.ASYNC);
  }

  /**
   * Create a sync thread factory that every thread runs in synchronously with
   * {@link BukkitScheduler#runTask(Plugin, Runnable)}.
   * 
   * @param plugin The basic plugin to assign to the created thread factory
   * 
   * @return the newly creted ThreadFactory
   */
  public static ThreadFactory ofSync(BasePlugin plugin) {
    return new BukkitThreadFactory(plugin, TaskContext.SYNC);
  }

  @Override
  public Thread newThread(Runnable runnable) {
    Thread thread = new Thread(() -> {
      try {
        context.run(plugin, runnable);
      } catch (Throwable t) {
        plugin.treatThrowable(this.getClass(), t, "Catched exception while runnin this thread.");
      }
    });
    thread.setDaemon(false);
    thread.setName(this.getClass().getSimpleName() + "> " + runnable.getClass().getSimpleName());
    return thread;
  }

  @Override
  public BasePlugin getPlugin() {
    return plugin;
  }

  public TaskContext getContext() {
    return context;
  }

}
