package com.janosgyerik.examples.tree.binarytree;

public class BinarySearchTreeImplTest extends BinarySearchTreeTest {
    @Override
    protected <T extends Comparable<T>> BinarySearchTree<T> newBinarySearchTree() {
        return new BinarySearchTreeImpl<>();
    }
}
