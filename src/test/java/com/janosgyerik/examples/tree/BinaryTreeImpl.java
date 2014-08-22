package com.janosgyerik.examples.tree;

import java.util.*;

public class BinaryTreeImpl<T extends Comparable<T>> implements BinaryTree<T> {

	private BinaryTreeNode<T> root = null;

	@Override
	public void add(T item) {
		if (root == null) {
			root = new BinaryTreeNode<T>(item);
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
				node.setLeft(new BinaryTreeNode<T>(data));
			} else {
				add(node.getLeft(), data);
			}
		} else {
			if (node.getRight() == null) {
				node.setRight(new BinaryTreeNode<T>(data));
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
