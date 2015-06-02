package com.janosgyerik.examples.tree.binarytree;

public class TreeNode<T> {
    public final T value;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T x) {
        value = x;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
