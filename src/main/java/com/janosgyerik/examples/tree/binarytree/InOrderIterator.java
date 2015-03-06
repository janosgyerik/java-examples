package com.janosgyerik.examples.tree.binarytree;

import java.util.Iterator;
import java.util.Stack;

public class InOrderIterator<T> implements Iterator<T> {

    private final Stack<TreeNode<T>> stack = new Stack<>();

    private TreeNode<T> current;

    public InOrderIterator(TreeNode<T> root) {
        current = root;
        if (current != null) {
            while (current.left != null) {
                stack.push(current);
                current = current.left;
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
        current = current.right;
        if (current != null) {
            while (current.left != null) {
                stack.push(current);
                current = current.left;
            }
        } else if (!stack.isEmpty()) {
            current = stack.pop();
        }
        return nextNode.val;
    }
}
