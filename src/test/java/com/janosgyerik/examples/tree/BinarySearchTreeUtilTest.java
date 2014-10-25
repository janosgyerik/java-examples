package com.janosgyerik.examples.tree;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinarySearchTreeUtilTest {
    @Test
    public void testSingleNodeIsBST() {
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(3);
        assertTrue(BinarySearchTreeUtil.isBinarySearchTree(node));
    }

    @Test
    public void testTrueBST() {
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(3);
        node.setLeft(new BinaryTreeNode<>(1));
        node.setRight(new BinaryTreeNode<>(5));
        assertTrue(BinarySearchTreeUtil.isBinarySearchTree(node));
    }

    @Test
    public void testNonBST_IfWrongOrder() {
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(3);
        node.setLeft(new BinaryTreeNode<>(5));
        assertFalse(BinarySearchTreeUtil.isBinarySearchTree(node));
    }

    @Test
    public void testNonBST_IfLeftIsDup() {
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(5);
        node.setLeft(new BinaryTreeNode<>(5));
        assertFalse(BinarySearchTreeUtil.isBinarySearchTree(node));
    }

    @Test
    public void testNonBST_IfRightIsDup() {
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(5);
        node.setRight(new BinaryTreeNode<>(5));
        assertFalse(BinarySearchTreeUtil.isBinarySearchTree(node));
    }

    @Test(expected = NullPointerException.class)
    public void testCannotCompareIntegerWithNull() {
        new Integer(3).compareTo(null);
    }
}
