package com.janosgyerik.examples.tree.binarytree;

import java.util.Collection;

public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

    private TreeNode<T> root = null;

    @Override
    public TreeNode<T> getRoot() {
        return root;
    }

    @Override
    public void insert(T val) {
        if (root == null) {
            root = new TreeNode<>(val);
        } else {
            insert(root, val);
        }
    }

    @Override
    public void insertAll(Collection<T> values) {
        values.forEach(this::insert);
    }

    private void insert(TreeNode<T> node, T val) {
        if (val.compareTo(node.val) < 0) {
            if (node.left == null) {
                node.left = new TreeNode<>(val);
            } else {
                insert(node.left, val);
            }
        } else if (node.val.compareTo(val) < 0) {
            if (node.right == null) {
                node.right = new TreeNode<>(val);
            } else {
                insert(node.right, val);
            }
        }
    }

    @Override
    public boolean contains(T val) {
        return contains(root, val);
    }

    private boolean contains(TreeNode<T> node, T val) {
        if (node == null) {
            return false;
        }
        int compare = val.compareTo(node.val);
        if (compare == 0) {
            return true;
        }
        if (compare < 0) {
            return contains(node.left, val);
        }
        return contains(node.right, val);
    }

    @Override
    public void delete(T val) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
