package com.github.arthurfiorette.sinklibrary.tuple;

import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pair<L, R> implements Serializable {

  private static final long serialVersionUID = -1993674550325364004L;

  protected L left;

  protected R right;

  public static <L, R> Pair<L, R> pair(final L left, final R right) {
    return new Pair<>(left, right);
  }
}
