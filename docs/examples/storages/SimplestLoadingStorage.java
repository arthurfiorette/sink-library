package examples.storages;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.data.storage.gson.GsonLoadingStorage;
import com.github.arthurfiorette.sinklibrary.executor.BukkitExecutor;
import com.github.arthurfiorette.sinklibrary.executor.TaskContext;
import com.github.arthurfiorette.sinklibrary.uuid.UuidAdapter;
import com.google.gson.GsonBuilder;
import examples.SimpleModel;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SimplestLoadingStorage extends GsonLoadingStorage<UUID, SimpleModel> {

  /**
   * @see {@link com.google.common.cache.CacheBuilder}
   *
   * @param plugin
   */
  public SimplestLoadingStorage(final BasePlugin plugin) {
    super( // this plugin instance
      plugin,
      // Your database component, just be sure that the database is being
      // registered before this storage.
      plugin.getComponent(/* my database component */null),
      // The class of your instance
      SimpleModel.class,
      // Any java.util.concurrent.Executor instance, prefer to use
      // BukkitExecutor to run threads with the bukkit in control and an async
      // executor to you don't stop Bukkit main thread while accessing the
      // database, for example.
      BukkitExecutor.newSingleThreadExecutor(plugin, TaskContext.ASYNC),
      // The generator function. This function is called when the databse
      // return null for a key, meaning that it does not have this value
      // saved, so we have to create a new one.
      id -> {
        final SimpleModel model = new SimpleModel();
        model.setId(id);
        model.setName("Model A");
        return model;
      },
      // Here is where you pass the cache rules. In this example, i'll set
      // that the max size of it is 256 entities, also save back in the
      // dabase after 5min and enable the cache stats.
      //
      // See more: google.com/search?q=Guava+CacheBuilder
      b -> b.expireAfterAccess(5, TimeUnit.MINUTES).maximumSize(256).recordStats()
    );
    // As we are using UUID as keys, we can override the default gson instance
    // to use an faster UUID Adapter and pass any other preference
    // If you want use Mojang ids, use MojangIdAdapter instead.
    this.gson = new GsonBuilder().registerTypeAdapter(UUID.class, new UuidAdapter()).create();
  }
}
