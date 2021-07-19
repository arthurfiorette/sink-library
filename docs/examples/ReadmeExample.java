package examples;

import com.github.arthurfiorette.sinklibrary.components.SinkPlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BasePlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.ComponentLoader;
import com.github.arthurfiorette.sinklibrary.listener.SinkListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class ReadmeExample extends SinkPlugin {

  @Override
  protected ComponentLoader[] components() {
    return new ComponentLoader[] { () -> new MyListener(this) };
  }
}

// A simple class to be our listener
class MyListener extends SinkListener {

  public MyListener(final BasePlugin owner) {
    super(owner);
  }

  @Override
  @EventHandler
  public void onPlayerJoin(final PlayerJoinEvent event) {
    // On every player join event, get the player and send a message
    event.getPlayer().sendMessage("Hello World!");
  }
}
