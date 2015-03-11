package com.janosgyerik.examples.tree.binarytree;

public interface BinarySearchTree<T extends Comparable<T>> {

    TreeNode<T> getRoot();

    void insert(T val);

    boolean contains(T val);

    void delete(T val);
}
