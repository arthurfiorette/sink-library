package examples.starter;

import com.github.arthurfiorette.sinklibrary.core.SinkPlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.ComponentLoader;

public class Plugin extends SinkPlugin {

  @Override
  public ComponentLoader[] components() {
    // Pay attention that the Storage needs the Database component, so it must
    // be registered after it.
    return new ComponentLoader[] { () -> new Database(this), () -> new Storage(this) };
  }
}
