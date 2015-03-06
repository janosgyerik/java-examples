package com.janosgyerik.examples.tree.binarytree;

import java.util.Iterator;
import java.util.Stack;

public class PreOrderIterator<T> implements Iterator<T> {

    private final Stack<TreeNode<T>> stack = new Stack<>();

    private TreeNode<T> current;

    public PreOrderIterator(TreeNode<T> root) {
        current = root;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        TreeNode<T> nextNode = current;
        if (current.left != null) {
            if (current.right != null) {
                stack.push(current.right);
            }
            current = current.left;
        } else if (current.right != null) {
            current = current.right;
        } else if (!stack.isEmpty()) {
            current = stack.pop();
        } else {
            current = null;
        }
        return nextNode.val;
    }
}
