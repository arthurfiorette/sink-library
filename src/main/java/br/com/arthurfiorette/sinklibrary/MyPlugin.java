package br.com.arthurfiorette.sinklibrary;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.arthurfiorette.sinklibrary.listener.SinkListener;

public class MyPlugin extends SinkPlugin {

  private MyListener listener = new MyListener(this);

  @Override
  public void onEnable() {
    listener.register();
  }

  @Override
  public void onDisable() {}
}

class MyListener extends SinkListener {

  public MyListener(SinkPlugin owner) {
    super(owner);
  }

  @Override
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    event.getPlayer().sendMessage("Hello World!");
  }
}