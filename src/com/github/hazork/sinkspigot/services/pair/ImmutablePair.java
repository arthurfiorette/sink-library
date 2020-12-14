package com.github.hazork.sinkspigot.services.pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ImmutablePair<L, R> {

    protected L left;
    protected R right;

    protected ImmutablePair(L left, R right) {
	this.left = left;
	this.right = right;
    }

    public L getLeft() {
	return left;
    }

    public R getRight() {
	return right;
    }

    public Pair<L, R> mutable() {
	return mutable(this);
    }

    public static <L, R> Pair<L, R> mutable(ImmutablePair<L, R> pair) {
	return new Pair<>(pair.left, pair.right);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((left == null) ? 0 : left.hashCode());
	result = prime * result + ((right == null) ? 0 : right.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof ImmutablePair)) {
	    return false;
	}
	ImmutablePair<?, ?> other = (ImmutablePair<?, ?>) obj;
	if (left == null) {
	    if (other.left != null) {
		return false;
	    }
	} else if (!left.equals(other.left)) {
	    return false;
	}
	if (right == null) {
	    if (other.right != null) {
		return false;
	    }
	} else if (!right.equals(other.right)) {
	    return false;
	}
	return true;
    }

    public static <L, R> ImmutablePair<L, R> of(L left, R right) {
	return new ImmutablePair<>(left, right);
    }

    public static <T> List<ImmutablePair<T, T>> asImmutableNode(Collection<T> coll) {
	List<ImmutablePair<T, T>> pairList = new ArrayList<>();
	LinkedList<T> list = new LinkedList<>(coll);
	ImmutablePair<T, T> atual = ImmutablePair.of(null, null);
	while (!list.isEmpty()) {
	    if (atual.getLeft() == null) {
		atual.left = list.pollFirst();
	    } else if (atual.getRight() == null) {
		atual.right = list.pollFirst();
	    } else {
		pairList.add(atual);
		atual = ImmutablePair.of(atual.getRight(), null);
	    }
	}
	if (atual.getLeft() != null) {
	    pairList.add(atual);
	}
	return pairList;
    }

}
