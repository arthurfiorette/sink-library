package com.github.hazork.sinkspigot.services.pair;

public class ImmutablePair<L, R> {

    protected L left;
    protected R right;

    public ImmutablePair(L left, R right) {
	this.left = left;
	this.right = right;
    }

    public L getLeft() {
	return left;
    }

    public R getRight() {
	return right;
    }

    public static <L, R> ImmutablePair<L, R> of(L left, R right) {
	return new ImmutablePair<>(left, right);
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

}
