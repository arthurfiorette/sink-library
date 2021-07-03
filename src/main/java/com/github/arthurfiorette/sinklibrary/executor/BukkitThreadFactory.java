package com.github.arthurfiorette.sinklibrary.executor;

import com.github.arthurfiorette.sinklibrary.BasePlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import java.util.concurrent.ThreadFactory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

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

  private final BasePlugin plugin;
  private final TaskContext context;

  public BukkitThreadFactory(final BasePlugin plugin, final TaskContext context) {
    this.plugin = plugin;
    this.context = context;
  }

  /**
   * Create a async thread factory that every thread runs in asynchronously with
   * {@link BukkitScheduler#runTaskAsynchronously(Plugin, Runnable)}.
   *
   * @param plugin The basic plugin to assign to the created thread factory
   *
   * @return the newly created ThreadFactory
   */
  public static ThreadFactory ofAsync(final BasePlugin plugin) {
    return new BukkitThreadFactory(plugin, TaskContext.ASYNC);
  }

  /**
   * Create a sync thread factory that every thread runs in synchronously with
   * {@link BukkitScheduler#runTask(Plugin, Runnable)}.
   *
   * @param plugin The basic plugin to assign to the created thread factory
   *
   * @return the newly created ThreadFactory
   */
  public static ThreadFactory ofSync(final BasePlugin plugin) {
    return new BukkitThreadFactory(plugin, TaskContext.SYNC);
  }

  @Override
  public Thread newThread(final Runnable runnable) {
    final Thread thread = new Thread(
      () -> {
        try {
          this.context.run(this.plugin, runnable);
        } catch (final Throwable t) {
          this.plugin.treatThrowable(
              this.getClass(),
              t,
              "Catched exception while running this thread."
            );
        }
      }
    );
    thread.setDaemon(false);
    thread.setName(this.getClass().getSimpleName() + "> " + runnable.getClass().getSimpleName());
    return thread;
  }

  @Override
  public BasePlugin getBasePlugin() {
    return this.plugin;
  }

  public TaskContext getContext() {
    return this.context;
  }
}
