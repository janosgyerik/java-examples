package com.janosgyerik.examples.tree;

public class BinaryTreeNode<T extends Comparable<T>> {
	private final T data;
	private BinaryTreeNode<T> left = null;
	private BinaryTreeNode<T> right = null;

	BinaryTreeNode(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public BinaryTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}

	public BinaryTreeNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
}
