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

    private static class InOrderIterator<T> implements Iterator<T> {

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

    private static class PostOrderIterator<T> implements Iterator<T> {

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

    private static class LevelOrderIterator<T> implements Iterator<T> {

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
}
