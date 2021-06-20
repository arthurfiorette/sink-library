package com.github.arthurfiorette.sinklibrary.executor;

import static com.github.arthurfiorette.sinklibrary.executor.BukkitThreadFactory.ofAsync;
import static com.github.arthurfiorette.sinklibrary.executor.BukkitThreadFactory.ofSync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.bukkit.scheduler.BukkitScheduler;

import com.github.arthurfiorette.sinklibrary.plugin.BasePlugin;

/**
 * Simple interface to target all executors that runs with a
 * {@link BukkitScheduler}.
 * 
 * @author https://github.com/Hazork/sink-library/
 */
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
  public static ExecutorService newSyncCachedThreadPool(BasePlugin plugin) {
    return Executors.newCachedThreadPool(ofSync(plugin));
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
  public static ExecutorService newAsyncCachedThreadPool(BasePlugin plugin) {
    return Executors.newCachedThreadPool(ofAsync(plugin));
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
  public static ExecutorService newSyncFixedThreadPool(BasePlugin plugin, int nThreads) {
    return Executors.newFixedThreadPool(nThreads, ofSync(plugin));
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
  public static ExecutorService newAsyncFixedThreadPool(BasePlugin plugin, int nThreads) {
    return Executors.newFixedThreadPool(nThreads, ofAsync(plugin));
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
  public static ScheduledExecutorService newSyncScheduledThreadPool(BasePlugin plugin, int corePoolSize) {
    return Executors.newScheduledThreadPool(corePoolSize, ofSync(plugin));
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
  public static ScheduledExecutorService newAsyncScheduledThreadPool(BasePlugin plugin, int corePoolSize) {
    return Executors.newScheduledThreadPool(corePoolSize, ofAsync(plugin));
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
  public static ExecutorService newSyncSingleThreadExecutor(BasePlugin plugin) {
    return Executors.newSingleThreadExecutor(ofSync(plugin));
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
  public static ExecutorService newAsyncSingleThreadExecutor(BasePlugin plugin) {
    return Executors.newSingleThreadExecutor(ofAsync(plugin));
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
  public static ScheduledExecutorService newSyncSingleThreadScheduledExecutor(BasePlugin plugin) {
    return Executors.newSingleThreadScheduledExecutor(ofSync(plugin));
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
  public static ScheduledExecutorService newAsyncSingleThreadScheduledExecutor(BasePlugin plugin) {
    return Executors.newSingleThreadScheduledExecutor(ofAsync(plugin));
  }

}
