package com.janosgyerik.examples.tree;

import java.util.Collection;
import java.util.List;

public interface BinaryTree<T extends Comparable<T>> {
	void add(T item);

	void add(Iterable<T> items);

	List<T> toList();

	void rebalance();
}
