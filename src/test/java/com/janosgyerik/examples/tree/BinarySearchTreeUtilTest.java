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
		node.setLeft(new BinaryTreeNode<Integer>(1));
		node.setRight(new BinaryTreeNode<Integer>(5));
		assertTrue(BinarySearchTreeUtil.isBinarySearchTree(node));
	}

	@Test
	public void testNonBST() {
		BinaryTreeNode<Integer> node = new BinaryTreeNode<>(3);
		node.setLeft(new BinaryTreeNode<Integer>(5));
		assertFalse(BinarySearchTreeUtil.isBinarySearchTree(node));
	}
}
