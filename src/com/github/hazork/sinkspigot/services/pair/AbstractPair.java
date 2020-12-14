package com.github.hazork.sinkspigot.services.pair;

public abstract class AbstractPair<L, R> implements Pair<L, R> {

    protected L left;
    protected R right;

    public AbstractPair(L left, R right) {
	this.left = left;
	this.right = right;
    }

    @Override
    public L getLeft() {
	return left;
    }

    @Override
    public R getRight() {
	return right;
    }

    public Pair<L, R> toPair() {
	return this;
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
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	AbstractPair<?, ?> other = (AbstractPair<?, ?>) obj;
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
