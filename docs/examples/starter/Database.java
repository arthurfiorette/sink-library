package examples.starter;

import com.github.arthurfiorette.sinklibrary.interfaces.BasePlugin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class Database
    implements com.github.arthurfiorette.sinklibrary.data.database.Database<UUID, String[]> {

  private final BasePlugin plugin;

  public Database(final BasePlugin plugin) {
    this.plugin = plugin;
  }

  private final HashMap<UUID, String[]> database = new HashMap<>();

  /**
   * This is simple template, but this method is intended to open the database
   * connection
   */
  @Override
  public void enable() throws Exception {}

  /**
   * This is a simple template, but this method is intended to open the database
   * connection
   */
  @Override
  public void disable() throws Exception {}

  @Override
  public BasePlugin getBasePlugin() {
    return this.plugin;
  }

  @Override
  public void save(final UUID id, final String[] value) {
    this.database.put(id, value);
  }

  @Override
  public String[] get(final UUID id) {
    return this.database.get(id);
  }

  @Override
  public Collection<String[]> getMany(final Collection<UUID> ids) {
    final Collection<String[]> col = new ArrayList<>();
    for(final UUID id: ids) {
      col.add(this.database.get(id));
    }
    return col;
  }
}
