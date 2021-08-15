package com.github.arthurfiorette.sinklibrary.tuple;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public class ValidTriple<L, M, R> extends Triple<L, M, R> {

  private static final long serialVersionUID = -5117094581015545727L;

  public ValidTriple(@NonNull final L left, @NonNull final M middle, @NonNull final R right) {
    super(left, middle, right);
  }

  public static <L, M, R> ValidTriple<L, M, R> validTriple(final L left, final M middle, final R right) {
    return new ValidTriple<>(left, middle, right);
  }

  @Override
  public void setLeft(@NonNull final L left) {
    super.setLeft(left);
  }

  @Override
  public void setMiddle(@NonNull final M middle) {
    super.setMiddle(middle);
  }

  @Override
  public void setRight(@NonNull final R right) {
    super.setRight(right);
  }
}
