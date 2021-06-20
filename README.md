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
        src="https://img.shields.io/github/forks/ArthurFiorette/sink-library?logo=github&label=Forks"
        target="_blank"
        alt="Forks" /></a
  ></code>
  <code
    ><a href="https://github.com/ArthurFiorette/sink-library/issues"
      ><img
        src="https://img.shields.io/github/issues/ArthurFiorette/sink-library?logo=github&label=Issues"
        target="_blank"
        alt="Issues" /></a
  ></code>
  <code
    ><a href="https://github.com/ArthurFiorette/sink-library/stargazers"
      ><img
        src="https://img.shields.io/github/stars/ArthurFiorette/sink-library?logo=github&label=Stars"
        target="_blank"
        alt="Stars" /></a
  ></code>
  <code
    ><a href="https://github.com/ArthurFiorette/sink-library/blob/main/LICENSE"
      ><img
        src="https://img.shields.io/github/license/ArthurFiorette/sink-library?logo=githu&label=License"
        target="_blank"
        alt="License" /></a
  ></code>
</div>

#

<br />
<br />

#### `Sink-library` is a powerful and high-performance tool for building spigot plugins.

<br />

### Hello Example

```java
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.arthurfiorette.sinklibrary.listener.SinkListener;

public class MyPlugin extends SinkPlugin {

  // Create a MyListener instance
  private MyListener listener = new MyListener(this);

  @Override
  public void onEnable() {
    // Register the listener while enabling the plugin
    listener.register();
  }

  @Override
  public void onDisable() {}
}

// A simple class to be our listener
class MyListener extends SinkListener {

  public MyListener(SinkPlugin owner) {
    super(owner);
  }

  @Override
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    // On every player join event, get the player and send
    // the "Hello World!" message to her/him.
    event.getPlayer().sendMessage("Hello World!");
  }
}
```

### Installing

This library is hosted by **[JitPack](https://jitpack.io/#ArthurFiorette/sink-library)**.

> Replace `VERSION` with the latest [Github](https://github.com/ArthurFiorette/sink-library/releases) release.

```xml
<!-- Maven -->
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.ArthurFiorette</groupId>
    <artifactId>sink-library</artifactId>
    <version>VERSION</version>
  </dependency>
</dependencies>
```

```gradle
// Gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  implementation 'com.github.ArthurFiorette:sink-library:VERSION'
}
```

```sbt
// Sbt
resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies += "com.github.ArthurFiorette" % "sink-library" % "VERSION"
```
