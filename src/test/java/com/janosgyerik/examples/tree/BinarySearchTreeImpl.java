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
		if (root == null) {
			Iterator<T> iter = items.iterator();
			if (iter.hasNext()) {
				root = new BinaryTreeNode<>(iter.next());
			}
			while (iter.hasNext()) {
				add(iter.next());
			}
		} else {
			add(root, items);
		}
	}

	private void add(BinaryTreeNode<T> node, Iterable<T> items) {
		for (T item : items) {
			add(node, item);
		}
	}

	@Override
	public boolean contains(T item) {
		return contains(root, item);
	}

	public boolean contains(BinaryTreeNode<T> node, T item) {
		if (node == null) {
			return false;
		} else if (node.getData().equals(item)) {
			return true;
		}
		return contains(node.getLeft(), item) || contains(node.getRight(), item);
	}

	@Override
	public boolean remove(T data) {
		if (root == null) {
			return false;
		}
		if (data.equals(root.getData())) {
			if (root.getLeft() == null && root.getRight() == null) {
				root = null;
			} else if (root.getLeft() == null) {
				root = root.getRight();
			} else if (root.getRight() == null) {
				root = root.getLeft();
			} else {
				if (size(root.getLeft()) < size(root.getRight())) {
					BinaryTreeNode<T> old = root.getLeft();
					root = root.getRight();
					add(toList(old));
				} else {
					BinaryTreeNode<T> old = root.getRight();
					root = root.getLeft();
					add(toList(old));
				}
			}
			return true;
		}
		return remove(root, data);
	}

	public boolean remove(BinaryTreeNode<T> node, T data) {
		if (data.compareTo(node.getData()) < 0) {
			if (node.getLeft() == null) {
				return false;
			} else if (node.getLeft().getData().equals(data)) {
				BinaryTreeNode<T> left = node.getLeft();
				node.setLeft(null);
				add(node, toList(left.getLeft()));
				add(node, toList(left.getRight()));
				return true;
			}
			return remove(node.getLeft(), data);
		} else {
			if (node.getRight() == null) {
				return false;
			} else if (node.getRight().getData().equals(data)) {
				BinaryTreeNode<T> right = node.getRight();
				node.setRight(null);
				add(node, toList(right.getLeft()));
				add(node, toList(right.getRight()));
				return true;
			}
			return remove(node.getRight(), data);
		}
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(BinaryTreeNode<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + size(node.getLeft()) + size(node.getRight());
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
		return toList(root);
	}

	private List<T> toList(BinaryTreeNode<T> node) {
		if (node == null) {
			return Collections.emptyList();
		}
		List<T> list = new ArrayList<>();
		list.addAll(toList(node.getLeft()));
		list.add(node.getData());
		list.addAll(toList(node.getRight()));
		return list;
	}

	@Override
	public void rebalance() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public String toString() {
		return toString(root);
	}

	private String toString(BinaryTreeNode<T> node) {
		StringBuilder builder = new StringBuilder("[");
		if (node == null) {
			builder.append((String) null).append("]");
			return builder.toString();
		}
		builder.append(toString(node.getLeft()));
		builder.append(",").append(node.getData()).append(",");
		builder.append(toString(node.getRight()));
		builder.append("]");
		return builder.toString();
	}
}
