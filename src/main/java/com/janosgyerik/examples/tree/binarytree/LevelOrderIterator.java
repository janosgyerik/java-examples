package com.janosgyerik.examples.tree.binarytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderIterator<T> implements Iterator<T> {

    private final Queue<TreeNode<T>> queue = new LinkedList<>();

    private TreeNode<T> current;

    public LevelOrderIterator(TreeNode<T> root) {
        current = root;
        if (current != null) {
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        TreeNode<T> nextNode = current;
        if (!queue.isEmpty()) {
            current = queue.poll();
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        } else {
            current = null;
        }
        return nextNode.val;
    }
}
