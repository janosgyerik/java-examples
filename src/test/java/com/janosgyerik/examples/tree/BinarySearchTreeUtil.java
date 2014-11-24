package com.janosgyerik.examples.tree;

public class BinarySearchTreeUtil {

    static boolean isBinarySearchTree(BinaryTreeNode<Integer> node) {
        return isBinarySearchTree(node, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    static <T extends Comparable<T>> boolean isBinarySearchTree(BinaryTreeNode<T> node, T maxData, T minData) {
        if (node == null) {
            return true;
        } else if (node.getData().compareTo(maxData) >= 0 || node.getData().compareTo(minData) <= 0) {
            return false;
        }

        return isBinarySearchTree(node.getLeft(), node.getData(), minData) && isBinarySearchTree(node.getRight(), maxData, node.getData());
    }
}
