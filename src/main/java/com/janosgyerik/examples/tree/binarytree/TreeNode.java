package com.janosgyerik.examples.tree.binarytree;

public class TreeNode<T> {
    public T val;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T x) {
        val = x;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
