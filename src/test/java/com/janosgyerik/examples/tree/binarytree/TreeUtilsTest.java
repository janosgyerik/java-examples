package com.janosgyerik.examples.tree.binarytree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TreeUtilsTest {

    @Test
    public void test_getHeight_empty() {
        assertEquals(0, TreeUtils.getHeight(null));
    }

    @Test
    public void test_getHeight_singleton() {
        assertEquals(1, TreeUtils.getHeight(new TreeNode<>(3)));
    }

    @Test
    public void test_getHeight_leftHigher() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(4);
        assertEquals(2, TreeUtils.getHeight(root));
    }

    @Test
    public void test_getHeight_rightHigher() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(4);
        root.right = new TreeNode<>(5);
        root.right.right = new TreeNode<>(6);
        assertEquals(3, TreeUtils.getHeight(root));
    }

    @Test
    public void test_isBinarySearchTree_empty() {
        assertTrue(TreeUtils.isBinarySearchTree(null, 1, 1));
    }

    @Test
    public void test_isBinarySearchTree_singleton() {
        assertTrue(TreeUtils.isBinarySearchTree(new TreeNode<>(3), Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_left() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(2);
        assertTrue(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_left_right() {
        TreeNode<Integer> root = new TreeNode<>(4);
        root.left = new TreeNode<>(2);
        root.left.right = new TreeNode<>(3);
        assertTrue(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_right_left() {
        TreeNode<Integer> root = new TreeNode<>(4);
        root.right = new TreeNode<>(6);
        root.right.left = new TreeNode<>(5);
        assertTrue(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_invalid_duplicates() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(3);
        assertFalse(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_invalid_left_right() {
        TreeNode<Integer> root = new TreeNode<>(4);
        root.left = new TreeNode<>(2);
        root.left.right = new TreeNode<>(5);
        assertFalse(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}