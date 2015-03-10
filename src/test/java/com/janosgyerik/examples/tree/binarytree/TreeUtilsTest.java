package com.janosgyerik.examples.tree.binarytree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeUtilsTest {

    @Test
    public void testGetHeight_empty() {
        assertEquals(0, TreeUtils.getHeight(null));
    }

    @Test
    public void testGetHeight_singleton() {
        assertEquals(1, TreeUtils.getHeight(new TreeNode<>(3)));
    }

    @Test
    public void testGetHeight_leftHigher() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(4);
        assertEquals(2, TreeUtils.getHeight(root));
    }

    @Test
    public void testGetHeight_rightHigher() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(4);
        root.right = new TreeNode<>(5);
        root.right.right = new TreeNode<>(6);
        assertEquals(3, TreeUtils.getHeight(root));
    }
}