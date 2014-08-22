package com.janosgyerik.examples.tree;

import java.util.*;

public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

	private BinaryTreeNode<T> root = null;

	@Override
	public void add(T item) {
		if (root == null) {
			root = new BinaryTreeNode<>(item);
		} else {
			add(root, item);
		}
	}

	public void add(BinaryTreeNode<T> node, T data) {
		int cmp = data.compareTo(node.getData());
		if (cmp == 0) {
			return;
		}
		if (cmp < 0) {
			if (node.getLeft() == null) {
				node.setLeft(new BinaryTreeNode<>(data));
			} else {
				add(node.getLeft(), data);
			}
		} else {
			if (node.getRight() == null) {
				node.setRight(new BinaryTreeNode<>(data));
			} else {
				add(node.getRight(), data);
			}
		}
	}

	@Override
	public void add(Iterable<T> items) {
		for (T item : items) {
			add(item);
		}
	}

	@Override
	public boolean contains(T item) {
		return false;
	}

	@Override
	public void remove(T item) {

	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public int height() {
		return height(root);
	}

	private int height(BinaryTreeNode<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
	}

	@Override
	public List<T> toList() {
		if (root == null) {
			return Collections.emptyList();
		}
		return toList(root);
	}

	private List<T> toList(BinaryTreeNode<T> node) {
		List<T> list = new ArrayList<>();
		if (node.getLeft() != null) {
			list.addAll(toList(node.getLeft()));
		}
		list.add(node.getData());
		if (node.getRight() != null) {
			list.addAll(toList(node.getRight()));
		}
		return list;
	}

	@Override
	public void rebalance() {

	}
}
