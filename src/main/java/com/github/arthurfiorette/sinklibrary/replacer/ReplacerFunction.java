package com.github.arthurfiorette.sinklibrary.replacer;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@FunctionalInterface
public interface ReplacerFunction extends UnaryOperator<Replacer> {

  default ReplacerFunction add(final String placeholder, final String value) {
    return r -> r.add(placeholder, value);
  }

  default ReplacerFunction add(final String placeholder, final Supplier<String> supplier) {
    return r -> r.add(placeholder, supplier);
  }

}
