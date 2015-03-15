package com.janosgyerik.examples.tree.binarytree;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
        if (root == null) {
            return;
        }
        List<TreeNode<T>> deleted = new LinkedList<>();
        if (root.val.equals(val)) {
            if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                // delete one of the branches and re-add elements to the other
                // TODO: find a better way to choose a branch to delete
                deleted.add(root.left);
                root = root.right;
            }
        } else {
            TreeNode<T> node = root;
            while (node != null) {
                if (node.val.compareTo(val) > 0) {
                    if (node.left != null && node.left.val.equals(val)) {
                        deleted.add(node.left.left);
                        deleted.add(node.left.right);
                        node.left = null;
                    }
                    node = node.left;
                } else {
                    if (node.right != null && node.right.val.equals(val)) {
                        deleted.add(node.right.left);
                        deleted.add(node.right.right);
                        node.right = null;
                    }
                    node = node.right;
                }
            }
        }
        for (TreeNode<T> node : deleted) {
            if (node == null) {
                continue;
            }
            Iterator<T> iterator = Iterators.levelOrderIterator(node);
            while (iterator.hasNext()) {
                insert(iterator.next());
            }
        }
    }
}
