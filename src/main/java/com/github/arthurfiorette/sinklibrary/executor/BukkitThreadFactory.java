package com.github.arthurfiorette.sinklibrary.executor;

import java.util.concurrent.ThreadFactory;

import com.github.arthurfiorette.sinklibrary.core.BaseModule;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;

import lombok.*;

/**
 * Every created thread from this object runs with the specified
 * {@link TaskContext}.
 * <p>
 * An object that creates new threads on demand. Using thread factories removes
 * hardwiring of calls to {@link Thread#Thread(Runnable) new Thread}, enabling
 * applications to use special thread subclasses, priorities, etc.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 *
 * @deprecated in flavor of
 * {@link com.github.arthurfiorette.sinklibrary.executor.v2.TaskContext} and
 * {@link BaseModule#getExecutor()}
 */
@Deprecated
@RequiredArgsConstructor
public class BukkitThreadFactory implements ThreadFactory, BaseComponent {

  @Getter
  @NonNull
  private final BasePlugin basePlugin;

  @Getter
  @NonNull
  private final TaskContext context;

  @Override
  public Thread newThread(final Runnable runnable) {
    final Thread thread = new Thread(
      () -> {
        try {
          this.context.run(this.basePlugin, runnable);
        } catch (final Throwable t) {
          this.basePlugin.treatThrowable(
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
}
