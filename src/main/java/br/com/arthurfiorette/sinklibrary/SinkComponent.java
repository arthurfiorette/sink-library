package br.com.arthurfiorette.sinklibrary;

/**
 * A class that implement this means that his instances need a SinkPlugin to
 * work well.
 *
 * @author https://github.com/Hazork/sink-library/
 */
public interface SinkComponent {

  /**
   * @return the {@link SinkPlugin} instance involved with this instance.
   */
  SinkPlugin getPlugin();

}
