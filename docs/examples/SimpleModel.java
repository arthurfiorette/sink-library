package examples;

import com.github.arthurfiorette.sinklibrary.uuid.FastUuid;
import java.util.Objects;
import java.util.UUID;

/**
 * Simple class model that contains an id and an name to be used in our examples
 */
public class SimpleModel {

  private UUID id;

  private String name;

  public SimpleModel() {}

  /**
   * Uses {@link FastUuid#toString(UUID)} to generate an string from this id
   * faster.
   */
  public String getIdString() {
    return FastUuid.toString(this.id);
  }

  public UUID getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setId(final UUID id) {
    this.id = id;
  }

  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof SimpleModel)) {
      return false;
    }
    final SimpleModel other = (SimpleModel) obj;
    return Objects.equals(this.id, other.id) && Objects.equals(this.name, other.name);
  }
}
