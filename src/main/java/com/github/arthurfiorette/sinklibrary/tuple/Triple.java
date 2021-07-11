package com.github.arthurfiorette.sinklibrary.tuple;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Triple<L, M, R> extends Pair<L, R> {

  private static final long serialVersionUID = -560234171949169145L;

  @Getter
  @Setter
  protected M middle;

  public Triple(final L left, final M middle, final R right) {
    super(left, right);
    this.middle = middle;
  }

  /**
   * Intended to be used with static imports
   */
  static <L, M, R> Triple<L, M, R> triple(final L left, final M middle, final R right) {
    return new Triple<>(left, middle, right);
  }
}
