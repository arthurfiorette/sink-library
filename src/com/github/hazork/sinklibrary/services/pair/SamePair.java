package com.github.hazork.sinklibrary.services.pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SamePair<T> extends AbstractPair<T, T> {

    public SamePair(T left, T right) {
	super(left, right);
    }

    @Override
    public Pair<T, T> toPair() {
	return this;
    }

    public static <T> SamePair<T> of(T left, T right) {
	return new SamePair<>(left, right);
    }

    public static <T> List<SamePair<T>> fromCollection(Collection<T> coll) {
	List<SamePair<T>> pairList = new ArrayList<>();
	LinkedList<T> list = new LinkedList<>(coll);
	SamePair<T> atual = new SamePair<>(null, null);
	while (!list.isEmpty()) {
	    if (atual.getLeft() == null) {
		atual.left = list.pollFirst();
	    } else if (atual.getRight() == null) {
		atual.right = list.pollFirst();
	    } else {
		pairList.add(atual);
		atual = new SamePair<>(atual.getRight(), null);
	    }
	}
	if (atual.getLeft() != null) {
	    pairList.add(atual);
	}
	return pairList;
    }
}
