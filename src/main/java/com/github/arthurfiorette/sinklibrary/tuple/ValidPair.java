package com.github.arthurfiorette.sinklibrary.tuple;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public class ValidPair<L, R> extends Pair<L, R> {

  private static final long serialVersionUID = 1496608312078098547L;

  public ValidPair(@NonNull final L left, @NonNull final R right) {
    super(left, right);
  }

  public static <L, R> ValidPair<L, R> validPair(final L left, final R right) {
    return new ValidPair<>(left, right);
  }

  @Override
  public void setLeft(@NonNull final L left) {
    super.setLeft(left);
  }

  @Override
  public void setRight(@NonNull final R right) {
    super.setRight(right);
  }
}
