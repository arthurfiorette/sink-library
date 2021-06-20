package com.github.arthurfiorette.sinklibrary;

import com.github.arthurfiorette.sinklibrary.executor.BukkitExecutor;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

public class ServiceCoordinator implements BaseComponent {

  private final LinkedList<BaseService> services = new LinkedList<>();
  private final BasePlugin plugin;
  private final boolean enabled = false;

  public ServiceCoordinator(BasePlugin plugin) {
    this.plugin = plugin;
  }

  private void enableService(BaseService s) {
    try {
      s.enable();
    } catch (Throwable t) {
      this.plugin.treatThrowable(s.getClass(), t, "Throwable catch while enabling a service");
    }
  }

  private void disableService(BaseService s) {
    try {
      s.disable();
    } catch (Throwable t) {
      this.plugin.treatThrowable(s.getClass(), t, "Throwable catch while disabling a service");
    }
  }

  public void add(BaseService[] services) {
    if (this.enabled) {
      this.plugin.log(
          Level.INFO,
          "%s services added to this coordinator while we are already enabled",
          services.length
        );
    }
    for (BaseService service : services) {
      if (this.services.contains(service)) {
        this.plugin.treatThrowable(
            service.getClass(),
            new IllegalArgumentException(),
            "We only support one service per class, and for a clean code, you should too."
          );
        return;
      }

      this.services.addLast(service);
    }
  }

  public <T extends BaseService> T getService(Class<T> clazz) {
    for (BaseService service : this.services) {
      if (service.getClass().equals(clazz)) {
        return clazz.cast(service);
      }
    }
    return null;
  }

  public <T extends BaseService> boolean remove(Class<T> clazz, boolean disable) {
    if (this.enabled) {
      this.plugin.log(
          Level.INFO,
          "The $s service tried to be removed while we are enabled",
          clazz.getSimpleName()
        );
    }
    int index = 0;
    for (BaseService service : this.services) {
      if (service.getClass().equals(clazz)) {
        this.services.remove(index);
        if (disable) {
          this.disableService(service);
        }
        return true;
      }
      index++;
    }
    return false;
  }

  public CompletableFuture<Void> requestEnable() {
    if (this.enabled) {
      this.plugin.log(Level.SEVERE, "Requested an enable operation while we are enabled");
      return CompletableFuture.completedFuture(null);
    }
    return CompletableFuture.runAsync(
      () -> {
        this.services.forEach(this::enableService);
      },
      BukkitExecutor.newSyncSingleThreadExecutor(this.plugin)
    );
  }

  public CompletableFuture<Void> requestDisable() {
    if (!this.enabled) {
      this.plugin.log(Level.SEVERE, "Requested an disable operation while we are disabled");
      return CompletableFuture.completedFuture(null);
    }
    return CompletableFuture.runAsync(
      () -> {
        while (this.services.size() != 0) {
          BaseService s = this.services.pollLast();
          this.disableService(s);
        }
      },
      BukkitExecutor.newSyncSingleThreadExecutor(this.plugin)
    );
  }

  @Override
  public BasePlugin getPlugin() {
    return this.plugin;
  }
}
