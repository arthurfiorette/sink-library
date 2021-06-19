<div align="center">
  <pre>
  <br />
  <h1>💧⚡🌊
  Sink Library</h1>
  <br />
  </pre>
  <br />
  <br />
  <code
    ><a href="https://github.com/ArthurFiorette/sink-library/network/members"
      ><img
        src="https://img.shields.io/github/forks/ArthurFiorette/sink-library?logo=github&style=flat-square&label=Forks"
        target="_blank"
        alt="Forks" /></a
  ></code>
  <code
    ><a href="https://github.com/ArthurFiorette/sink-library/issues"
      ><img
        src="https://img.shields.io/github/issues/ArthurFiorette/sink-library?logo=github&style=flat-square&label=Issues"
        target="_blank"
        alt="Issues" /></a
  ></code>
  <code
    ><a href="https://github.com/ArthurFiorette/sink-library/stargazers"
      ><img
        src="https://img.shields.io/github/stars/ArthurFiorette/sink-library?logo=github&style=flat-square&label=Stars"
        target="_blank"
        alt="Stars" /></a
  ></code>
  <code
    ><a href="https://github.com/ArthurFiorette/sink-library/blob/main/LICENSE"
      ><img
        src="https://img.shields.io/github/license/ArthurFiorette/sink-library?logo=github&style=flat-square&label=License"
        target="_blank"
        alt="License" /></a
  ></code>
</div>

#

<br />
<br />

#### `Sink-library` is a powerful and hight-performance tool for building spigot plugins.

<br />
<br />

```java
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
```
