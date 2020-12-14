package com.github.hazork.sinkapi.services.pair;

public class SimplePair<L, R> extends AbstractPair<L, R> {

    public SimplePair(L left, R right) {
	super(left, right);
    }

    @Override
    public Pair<L, R> toPair() {
	return this;
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
	return new SimplePair<>(left, right);
    }

}
