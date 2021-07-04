package examples.starter;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.data.storage.AbstractStorage;
import com.github.arthurfiorette.sinklibrary.executor.BukkitExecutor;
import com.github.arthurfiorette.sinklibrary.executor.TaskContext;
import com.github.arthurfiorette.sinklibrary.uuid.FastUuid;
import examples.SimpleModel;
import java.util.UUID;

/**
 * Simple storage example, see more in docs/examples/storages
 *
 * @author https://github.com/Hazork/sink-library/
 */
public class Storage extends AbstractStorage<UUID, SimpleModel, String[]> {

  private final BasePlugin plugin;

  /**
   * Note that we only use the plugin as an argument, and get the database after
   * it. with {@link BasePlugin#getComponent(Class)} or
   * {@link BasePlugin#getService(Class)}
   */
  public Storage(final BasePlugin plugin) {
    super(
      plugin.getComponent(Database.class),
      BukkitExecutor.newFixedThreadPool(plugin, TaskContext.ASYNC, 2)
    );
    this.plugin = plugin;
  }

  /**
   * Simple serialization example.
   *
   * @see /docs/examples/starter for advanced examples
   */
  @Override
  public String[] serialize(final SimpleModel obj) {
    return new String[] { obj.getIdString(), obj.getName() };
  }

  /**
   * Simple deserialization example. see SerializingExample.java for an advanced
   * one.
   * <p>
   * Uses {@link FastUuid#toString(UUID)} to generate an UUID from the string id
   * faster.
   *
   * @see SerializingExamples for advanced examples to generate an id
   * from this string faster
   */
  @Override
  public SimpleModel deserialize(final String[] args) {
    final SimpleModel obj = new SimpleModel();
    obj.setId(FastUuid.parseUUID(args[0]));
    obj.setName(args[1]);
    return obj;
  }

  /**
   * This method is called when is requested an new MyObject that our database
   * doesn't have, so we have to create a new.
   */
  @Override
  protected SimpleModel create(final UUID id) {
    final SimpleModel obj = new SimpleModel();
    obj.setId(id);
    obj.setName("Foo");
    return obj;
  }

  @Override
  public BasePlugin getBasePlugin() {
    return this.plugin;
  }
}
