package br.com.arthurfiorette.sinklibrary.scheduler;

import br.com.arthurfiorette.sinklibrary.SinkComponent;

/**
 * A interface that helps to schedule runnables.
 *
 * @author https://github.com/Hazork/sink-library/
 */
public interface SinkScheduler extends SinkComponent {

  default void runTask(Runnable task) {
    TaskScheduler.SYNC.run(this.getPlugin(), task);
  }

  default void runTask(Runnable task, long delay) {
    TaskScheduler.SYNC.run(this.getPlugin(), task, delay);
  }

  default void runTask(Runnable task, long delay, long interval) {
    TaskScheduler.SYNC.run(this.getPlugin(), task, delay, interval);
  }

  default void runAsyncTask(Runnable task) {
    TaskScheduler.ASYNC.run(this.getPlugin(), task);
  }

  default void runAsyncTask(Runnable task, long delay) {
    TaskScheduler.ASYNC.run(this.getPlugin(), task, delay);
  }

  default void runAsyncTask(Runnable task, long delay, long interval) {
    TaskScheduler.ASYNC.run(this.getPlugin(), task, delay, interval);
  }

}
