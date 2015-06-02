package com.janosgyerik.examples.tree.binarytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Iterators {

    private Iterators() {
        // utility class, forbidden constructor
    }

    public static <T> Iterator<T> preOrderIterator(TreeNode<T> root) {
        return new PreOrderIterator<>(root);
    }

    public static <T> Iterator<T> inOrderIterator(TreeNode<T> root) {
        return new InOrderIterator<>(root);
    }

    public static <T> Iterator<T> postOrderIterator(TreeNode<T> root) {
        return new PostOrderIterator<>(root);
    }

    public static <T> Iterator<T> levelOrderIterator(TreeNode<T> root) {
        return new LevelOrderIterator<>(root);
    }

    private static class PreOrderIterator<T> implements Iterator<T> {
        private final Stack<TreeNode<T>> stack = new Stack<>();

        private PreOrderIterator(TreeNode<T> root) {
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            TreeNode<T> node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            return node.value;
        }
    }

    private static class InOrderIterator<T> implements Iterator<T> {
        private Stack<TreeNode<T>> stack = new Stack<>();

        private InOrderIterator(TreeNode<T> root) {
            moveToLeftMost(root);
        }

        private void moveToLeftMost(TreeNode<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            TreeNode<T> current = stack.pop();
            moveToLeftMost(current.right);
            return current.value;
        }
    }

    private static class PostOrderIterator<T> implements Iterator<T> {
        private Stack<TreeNode<T>> stack = new Stack<>();

        private PostOrderIterator(TreeNode<T> root) {
            moveToNextLeaf(root);
        }

        private void moveToNextLeaf(TreeNode<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left != null ? node.left : node.right;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            TreeNode<T> current = stack.pop();
            if (!stack.isEmpty()) {
                TreeNode<T> parent = stack.peek();
                if (parent.right != current) {
                    moveToNextLeaf(parent.right);
                }
            }
            return current.value;
        }
    }

    private static class LevelOrderIterator<T> implements Iterator<T> {
        private final Queue<TreeNode<T>> queue = new LinkedList<>();

        private LevelOrderIterator(TreeNode<T> root) {
            queue.add(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            TreeNode<T> current = queue.poll();

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }

            return current.value;
        }
    }
}
