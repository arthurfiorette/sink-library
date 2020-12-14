package com.github.hazork.sinkspigot.services.pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

    public ImmutablePair<L, R> immutable() {
	return immutable(this);
    }

    public static <L, R> ImmutablePair<L, R> immutable(Pair<L, R> pair) {
	return new ImmutablePair<>(pair.left, pair.right);
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
	return new Pair<>(left, right);
    }

    public static Pair<?, ?> empty() {
	return of(null, null);
    }

    public static <T> List<Pair<T, T>> asNode(Collection<T> coll) {
	List<Pair<T, T>> pairList = new ArrayList<>();
	LinkedList<T> list = new LinkedList<>(coll);
	Pair<T, T> atual = Pair.of(null, null);
	while (!list.isEmpty()) {
	    if (atual.getLeft() == null) {
		atual.left = list.pollFirst();
	    } else if (atual.getRight() == null) {
		atual.right = list.pollFirst();
	    } else {
		pairList.add(atual);
		atual = Pair.of(atual.getRight(), null);
	    }
	}
	if (atual.getLeft() != null) {
	    pairList.add(atual);
	}
	return pairList;
    }

}