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
   * Creates a sync thread pool that creates new threads as needed, but will
   * reuse previously constructed threads when they are available.
   *
   * @see {@link BukkitThreadFactory}
   *
   * @param plugin The basic plugin to assign to the created thread factory
   *
   * @return the newly created thread pool
   */
  public ExecutorService newSyncCachedThreadPool(final BasePlugin plugin) {
    return Executors.newCachedThreadPool(BukkitThreadFactory.ofSync(plugin));
  }

  /**
   * Creates a async thread pool that creates new threads as needed, but will
   * reuse previously constructed threads when they are available.
   *
   * @see {@link BukkitThreadFactory}
   *
   * @param plugin The basic plugin to assign to the created thread factory
   *
   * @return the newly created thread pool
   */
  public ExecutorService newAsyncCachedThreadPool(final BasePlugin plugin) {
    return Executors.newCachedThreadPool(BukkitThreadFactory.ofAsync(plugin));
  }

  /**
   * Creates a sync thread pool that reuses a fixed number of threads operating
   * off a shared unbounded queue, using the provided ThreadFactory to create
   * new threads when needed. At any point, at most {@code nThreads} threads
   * will be active processing tasks. If additional tasks are submitted when all
   * threads are active, they will wait in the queue until a thread is
   * available. If any thread terminates due to a failure during execution prior
   * to shutdown, a new one will take its place if needed to execute subsequent
   * tasks. The threads in the pool will exist until it is explicitly
   * {@link ExecutorService#shutdown shutdown}.
   *
   * @param nThreads the number of threads in the pool
   * @param plugin The basic plugin to assign to the created thread factory
   *
   * @return the newly created thread pool
   *
   * @throws IllegalArgumentException if {@code nThreads <= 0}
   */
  public ExecutorService newSyncFixedThreadPool(final BasePlugin plugin, final int nThreads) {
    return Executors.newFixedThreadPool(nThreads, BukkitThreadFactory.ofSync(plugin));
  }

  /**
   * Creates a async thread pool that reuses a fixed number of threads operating
   * off a shared unbounded queue, using the provided ThreadFactory to create
   * new threads when needed. At any point, at most {@code nThreads} threads
   * will be active processing tasks. If additional tasks are submitted when all
   * threads are active, they will wait in the queue until a thread is
   * available. If any thread terminates due to a failure during execution prior
   * to shutdown, a new one will take its place if needed to execute subsequent
   * tasks. The threads in the pool will exist until it is explicitly
   * {@link ExecutorService#shutdown shutdown}.
   *
   * @param nThreads the number of threads in the pool
   * @param plugin The basic plugin to assign to the created thread factory
   *
   * @return the newly created thread pool
   *
   * @throws IllegalArgumentException if {@code nThreads <= 0}
   */
  public ExecutorService newAsyncFixedThreadPool(final BasePlugin plugin, final int nThreads) {
    return Executors.newFixedThreadPool(nThreads, BukkitThreadFactory.ofAsync(plugin));
  }

  /**
   * Creates a sync thread pool that can schedule commands to run after a given
   * delay, or to execute periodically.
   *
   * @param plugin The basic plugin to assign to the created thread factory
   * @param corePoolSize the number of threads to keep in the pool, even if they
   * are idle
   *
   * @return a newly created scheduled thread pool
   *
   * @throws IllegalArgumentException if {@code corePoolSize < 0}
   */
  public ScheduledExecutorService newSyncScheduledThreadPool(
    final BasePlugin plugin,
    final int corePoolSize
  ) {
    return Executors.newScheduledThreadPool(corePoolSize, BukkitThreadFactory.ofSync(plugin));
  }

  /**
   * Creates a async thread pool that can schedule commands to run after a given
   * delay, or to execute periodically.
   *
   * @param plugin The basic plugin to assign to the created thread factory
   * @param corePoolSize the number of threads to keep in the pool, even if they
   * are idle
   *
   * @return a newly created scheduled thread pool
   *
   * @throws IllegalArgumentException if {@code corePoolSize < 0}
   */
  public ScheduledExecutorService newAsyncScheduledThreadPool(
    final BasePlugin plugin,
    final int corePoolSize
  ) {
    return Executors.newScheduledThreadPool(corePoolSize, BukkitThreadFactory.ofAsync(plugin));
  }

  /**
   * Creates an sync Executor that uses a single worker thread operating off an
   * unbounded queue, and uses the provided ThreadFactory to create a new thread
   * when needed. Unlike the otherwise equivalent
   * {@code newFixedThreadPool(1, threadFactory)} the returned executor is
   * guaranteed not to be reconfigurable to use additional threads.
   *
   * @param plugin The basic plugin to assign to the created thread factory
   *
   * @return the newly created single-threaded Executor
   */
  public ExecutorService newSyncSingleThreadExecutor(final BasePlugin plugin) {
    return Executors.newSingleThreadExecutor(BukkitThreadFactory.ofSync(plugin));
  }

  /**
   * Creates an async Executor that uses a single worker thread operating off an
   * unbounded queue, and uses the provided ThreadFactory to create a new thread
   * when needed. Unlike the otherwise equivalent
   * {@code newFixedThreadPool(1, threadFactory)} the returned executor is
   * guaranteed not to be reconfigurable to use additional threads.
   *
   * @param plugin The basic plugin to assign to the created thread factory
   *
   * @return the newly created single-threaded Executor
   */
  public ExecutorService newAsyncSingleThreadExecutor(final BasePlugin plugin) {
    return Executors.newSingleThreadExecutor(BukkitThreadFactory.ofAsync(plugin));
  }

  /**
   * Creates a sync single-threaded executor that can schedule commands to run
   * after a given delay, or to execute periodically. (Note however that if this
   * single thread terminates due to a failure during execution prior to
   * shutdown, a new one will take its place if needed to execute subsequent
   * tasks.) Tasks are guaranteed to execute sequentially, and no more than one
   * task will be active at any given time.
   * <p>
   * Unlike the otherwise equivalent
   * {@link BukkitExecutor#newSyncScheduledThreadPool(BasePlugin, int)} the
   * returned executor is guaranteed not to be reconfigurable to use additional
   * threads.
   *
   * @param plugin The basic plugin to assign to the created thread factory
   *
   * @return a newly created scheduled executor
   */
  public ScheduledExecutorService newSyncSingleThreadScheduledExecutor(final BasePlugin plugin) {
    return Executors.newSingleThreadScheduledExecutor(BukkitThreadFactory.ofSync(plugin));
  }

  /**
   * Creates a async single-threaded executor that can schedule commands to run
   * after a given delay, or to execute periodically. (Note however that if this
   * single thread terminates due to a failure during execution prior to
   * shutdown, a new one will take its place if needed to execute subsequent
   * tasks.) Tasks are guaranteed to execute sequentially, and no more than one
   * task will be active at any given time.
   * <p>
   * Unlike the otherwise equivalent
   * {@link BukkitExecutor#newAsyncScheduledThreadPool(BasePlugin, int)} the
   * returned executor is guaranteed not to be reconfigurable to use additional
   * threads.
   *
   * @param plugin The basic plugin to assign to the created thread factory
   *
   * @return a newly created scheduled executor
   */
  public ScheduledExecutorService newAsyncSingleThreadScheduledExecutor(final BasePlugin plugin) {
    return Executors.newSingleThreadScheduledExecutor(BukkitThreadFactory.ofAsync(plugin));
  }
}
