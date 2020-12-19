package com.github.hazork.sinkspigot.services.pair;

public class Pair<L, R> extends ImmutablePair<L, R> {

    protected Pair(L left, R right) {
	super(left, right);
    }

    public void setLeft(L left) {
	this.left = left;
    }

    public void setRight(R right) {
	this.right = right;
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
	return new Pair<>(left, right);
    }

    public static Pair<?, ?> empty() {
	return of(null, null);
    }

}