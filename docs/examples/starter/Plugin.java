package examples.starter;

import static com.github.arthurfiorette.sinklibrary.component.loaders.ComponentLoader.reflect;

import com.github.arthurfiorette.sinklibrary.component.loaders.ComponentLoader;
import com.github.arthurfiorette.sinklibrary.core.SinkPlugin;

public class Plugin extends SinkPlugin {

  @Override
  public ComponentLoader[] components() {
    // Pay attention that the Storage needs the Database component, so it must
    // be registered after it.
    return reflect(this, Database.class, Storage.class);
  }
}
