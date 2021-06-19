package br.com.arthurfiorette.sinklibrary.config;

import br.com.arthurfiorette.sinklibrary.SinkComponent;

/**
 * an interface to deliever useful methods from Yml files and container
 *
 * @author https://github.com/Hazork/sink-library/
 */
public interface YmlHelper extends SinkComponent {

  /**
   * @return the yml container from this plugin.
   */
  default YmlContainer getYmlContainer() {
    return this.getPlugin().getYmlContainer();
  }

  /**
   * @param name the yml file
   * 
   * @return the yml file associated with this name or null if not found.
   */
  default YmlFile getYmlFile(String name) {
    return this.getYmlContainer().getFile(name);
  }

}
