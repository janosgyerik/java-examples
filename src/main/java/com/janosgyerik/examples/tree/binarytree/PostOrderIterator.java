package com.janosgyerik.examples.tree.binarytree;

import java.util.Iterator;
import java.util.Stack;

public class PostOrderIterator<T> implements Iterator<T> {

    private final Stack<TreeNode<T>> stack = new Stack<>();

    private TreeNode<T> current;

    public PostOrderIterator(TreeNode<T> root) {
        current = root;
        if (current != null) {
            while (current.left != null || current.right != null) {
                stack.push(current);
                if (current.left != null) {
                    current = current.left;
                } else {
                    current = current.right;
                }
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
        if (!stack.isEmpty()) {
            current = stack.pop();
            if (current.right != null && current.right != nextNode) {
                stack.push(current);
                current = current.right;
                while (current.left != null || current.right != null) {
                    stack.push(current);
                    if (current.left != null) {
                        current = current.left;
                    } else {
                        current = current.right;
                    }
                }
            }
        } else {
            current = null;
        }
        return nextNode.val;
    }
}
