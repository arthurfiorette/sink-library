package examples;

import static com.github.arthurfiorette.sinklibrary.component.loaders.ComponentLoader.reflect;

import com.github.arthurfiorette.sinklibrary.component.loaders.ComponentLoader;
import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.core.SinkPlugin;
import com.github.arthurfiorette.sinklibrary.events.SinkListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class ReadmeExample extends SinkPlugin {

  @Override
  public ComponentLoader[] components() {
    // A helper method to create a component loader array based on reflection
    return reflect(this, MyListener.class);
  }
}

// A simple class to be our listener
class MyListener implements SinkListener {

  private final BasePlugin plugin;

  public MyListener(BasePlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onPlayerJoin(final PlayerJoinEvent event) {
    // On every player join event, get the player and send a message
    event.getPlayer().sendMessage("Hello World!");
  }

  @Override
  public BasePlugin getBasePlugin() {
    return plugin;
  }
}
