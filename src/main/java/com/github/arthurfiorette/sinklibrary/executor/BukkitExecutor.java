package com.github.arthurfiorette.sinklibrary.executor;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import lombok.experimental.UtilityClass;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Simple interface to target all executors that runs with a
 * {@link BukkitScheduler}.
 */
@UtilityClass
public final class BukkitExecutor {

  /**
   * Creates a thread pool that creates new threads as needed, but will reuse
   * previously constructed threads when they are available.
   *
   * @see BukkitThreadFactory
   *
   * @param plugin The basic plugin to assign to the created thread factory
   * @param context The thread context to be used
   *
   * @return the newly created thread pool
   */
  public ExecutorService newCachedThreadPool(final BasePlugin plugin, final TaskContext context) {
    return Executors.newCachedThreadPool(new BukkitThreadFactory(plugin, context));
  }

  /**
   * Creates a thread pool that reuses a fixed number of threads operating off a
   * shared unbounded queue, using the provided ThreadFactory to create new
   * threads when needed. At any point, at most {@code nThreads} threads will be
   * active processing tasks. If additional tasks are submitted when all threads
   * are active, they will wait in the queue until a thread is available. If any
   * thread terminates due to a failure during execution prior to shutdown, a
   * new one will take its place if needed to execute subsequent tasks. The
   * threads in the pool will exist until it is explicitly
   * {@link ExecutorService#shutdown shutdown}.
   *
   * @param plugin The basic plugin to assign to the created thread factory
   * @param context The thread context to be used
   * @param nThreads the number of threads in the pool
   *
   * @return the newly created thread pool
   *
   * @throws IllegalArgumentException if {@code nThreads <= 0}
   */
  public ExecutorService newFixedThreadPool(final BasePlugin plugin, final TaskContext context, final int nThreads) {
    return Executors.newFixedThreadPool(nThreads, new BukkitThreadFactory(plugin, context));
  }

  /**
   * Creates a thread pool that can schedule commands to run after a given
   * delay, or to execute periodically.
   *
   * @param plugin The basic plugin to assign to the created thread factory
   * @param context The thread context to be used
   * @param corePoolSize the number of threads to keep in the pool, even if they
   * are idle
   *
   * @return a newly created scheduled thread pool
   *
   * @throws IllegalArgumentException if {@code corePoolSize < 0}
   */
  public ScheduledExecutorService newScheduledThreadPool(final BasePlugin plugin, final TaskContext context,
      final int corePoolSize) {
    return Executors.newScheduledThreadPool(corePoolSize, new BukkitThreadFactory(plugin, context));
  }

  /**
   * Creates an Executor that uses a single worker thread operating off an
   * unbounded queue, and uses the provided ThreadFactory to create a new thread
   * when needed. Unlike the otherwise equivalent
   * {@code newFixedThreadPool(1, threadFactory)} the returned executor is
   * guaranteed not to be reconfigurable to use additional threads.
   *
   * @param plugin The basic plugin to assign to the created thread factory
   * @param context The thread context to be used
   *
   * @return the newly created single-threaded Executor
   */
  public ExecutorService newSingleThreadExecutor(final BasePlugin plugin, final TaskContext context) {
    return Executors.newSingleThreadExecutor(new BukkitThreadFactory(plugin, context));
  }

  /**
   * Creates a single-threaded executor that can schedule commands to run after
   * a given delay, or to execute periodically. (Note however that if this
   * single thread terminates due to a failure during execution prior to
   * shutdown, a new one will take its place if needed to execute subsequent
   * tasks.) Tasks are guaranteed to execute sequentially, and no more than one
   * task will be active at any given time.
   * <p>
   * Unlike the otherwise equivalent
   * {@link BukkitExecutor#newScheduledThreadPool(BasePlugin, TaskContext, int)}
   * the returned executor is guaranteed not to be reconfigurable to use
   * additional threads.
   *
   * @param plugin The basic plugin to assign to the created thread factory
   * @param context The thread context to be used
   *
   * @return a newly created scheduled executor
   */
  public ScheduledExecutorService newSingleThreadScheduledExecutor(final BasePlugin plugin, final TaskContext context) {
    return Executors.newSingleThreadScheduledExecutor(new BukkitThreadFactory(plugin, context));
  }
}
