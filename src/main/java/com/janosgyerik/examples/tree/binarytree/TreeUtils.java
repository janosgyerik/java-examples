package com.janosgyerik.examples.tree.binarytree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeUtils {

    private TreeUtils() {
        // utility class, forbidden constructor
    }

    // Something like this:
    /*
                      A
             B                  C
        D        E         F         G
     H    I    J    K    L    M    N    O
    P Q  R S  T U  V W  X Y  Z 1  2 3  4 5
     */
    public static void prettyPrint(TreeNode<?> root) {
        if (root == null) {
            return;
        }

        int height = getHeight(root);

        Queue<TreeNode<?>> queue = new LinkedList<>();
        queue.add(root);

        for (int level = 1; level <= height; ++level) {
            int paddingFirst = (int) Math.pow(2, (height - level));
            int paddingBetween = paddingFirst * 2 - 1;

            printPadding(paddingFirst);

            List<TreeNode<?>> copy = new LinkedList<>(queue);
            queue.clear();

            for (TreeNode<?> node : copy) {
                if (node == null) {
                    System.out.print(" ");
                    queue.add(null);
                    queue.add(null);
                } else {
                    System.out.print(node.val);
                    queue.add(node.left);
                    queue.add(node.right);
                }
                printPadding(paddingBetween);
            }
            System.out.println();
        }
    }

    private static void printPadding(int padding) {
        for (int i = 0; i < padding; ++i) {
            System.out.print(" ");
        }
    }

    public static int getHeight(TreeNode<?> root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public static <T extends Comparable<T>> boolean isBinarySearchTree(TreeNode<T> root, T minValue, T maxValue) {
        return root == null ||
                minValue.compareTo(root.val) < 0 && root.val.compareTo(maxValue) < 0
                && isBinarySearchTree(root.left, minValue, root.val)
                && isBinarySearchTree(root.right, root.val, maxValue);
    }
}
