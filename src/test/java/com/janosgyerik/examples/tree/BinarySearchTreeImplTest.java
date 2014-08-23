package com.janosgyerik.examples.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinarySearchTreeImplTest {
	@Test
	public void testOrdering() {
		assertEquals(Arrays.asList(5, 10, 15), toTree(15, 5, 10).toList());
	}

	@Test
	public void testOrdering10() {
		Random random = new Random(0);
		List<Integer> list = new ArrayList<>(10);
		for (int i = 0; i < 10; ++i) {
			list.add(random.nextInt(10));
		}
		assertEquals(Arrays.asList(0, 8, 9, 7, 5, 3, 1, 1, 9, 4), list);
		assertEquals(Arrays.asList(0, 1, 3, 4, 5, 7, 8, 9), toTreeToList(list));
	}

	@Test
	public void testRemovingNothing() {
		BinarySearchTreeImpl<Integer> tree = toTree(4, 3, 1, 5, 6, 2, 3, 7);
		assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), tree.toList());
		assertFalse(tree.remove(9));
		assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), tree.toList());
	}

	@Test
	public void testRemovingRoot() {
		BinarySearchTreeImpl<Integer> tree = toTree(1, 2, 3, 4);
		assertEquals(Arrays.asList(1, 2, 3, 4), tree.toList());
		assertTrue(tree.remove(1));
		assertEquals(Arrays.asList(2, 3, 4), tree.toList());
	}

	@Test
	public void testRemoving123() {
		BinarySearchTreeImpl<Integer> tree = toTree(4, 3, 1, 5, 6, 2, 3, 7);
		assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), tree.toList());
		assertTrue(tree.remove(1));
		assertEquals(Arrays.asList(2, 3, 4, 5, 6, 7), tree.toList());
		assertTrue(tree.remove(2));
		assertEquals(Arrays.asList(3, 4, 5, 6, 7), tree.toList());
		assertTrue(tree.remove(3));
		assertEquals(Arrays.asList(4, 5, 6, 7), tree.toList());
	}

	@Test
	public void testRemovingAll() {
		BinarySearchTreeImpl<Integer> tree = toTree(4, 3, 1, 5, 6, 2, 3, 7);
		assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), tree.toList());
		for (Integer item : tree.toList()) {
			assertTrue(tree.remove(item));
		}
		assertEquals(0, tree.toList().size());
	}

	@Test
	public void testEmpty() {
		assertEquals(Arrays.<Integer>asList(), toTree().toList());
	}

	@Test
	public void testNoDups() {
		assertEquals(Arrays.asList(3), toTree(3, 3, 3).toList());
		assertEquals(Arrays.asList(3, 4), toTree(3, 4, 4).toList());
		assertEquals(Arrays.asList(3, 4), toTree(4, 3, 4, 4).toList());
		assertEquals(Arrays.asList(3, 4), toTree(4, 4, 3, 4, 4).toList());
	}

	@Test
	public void testContains() {
		assertTrue(toTree(3).contains(3));
		assertTrue(toTree(3, 4, 5).contains(4));
		assertTrue(toTree(3, 4, 5).contains(5));
		assertTrue(toTree(3, 4, 5).contains(3));
		assertFalse(toTree(3, 4, 5).contains(6));
		assertFalse(toTree(3, 4, 5).contains(null));
	}

	@Test
	public void testHeight() {
		assertEquals(1, toTree(3).height());
		assertEquals(2, toTree(3, 4).height());
		assertEquals(3, toTree(3, 4, 5).height());
		assertEquals(4, toTree(3, 4, 5, 6).height());
		assertEquals(5, toTree(3, 4, 5, 6, 7).height());
		assertEquals(3, toTree(5, 3, 4, 6, 7).height());
		assertEquals(7, toTree(1, 2, 3, 4, 5, 6, 7).height());
		assertEquals(3, toTree(4, 2, 1, 3, 6, 5, 7).height());
	}

	@Test
	public void testSize() {
		assertEquals(1, toTree(3).size());
		assertEquals(1, toTree(3, 3, 3).size());
		assertEquals(2, toTree(3, 4).size());
		assertEquals(3, toTree(3, 4, 5).size());
		assertEquals(4, toTree(3, 4, 5, 6).size());
		assertEquals(5, toTree(3, 4, 5, 6, 7).size());
		assertEquals(5, toTree(5, 3, 4, 6, 7).size());
		assertEquals(7, toTree(1, 2, 3, 4, 5, 6, 7).size());
		assertEquals(7, toTree(4, 2, 1, 3, 6, 5, 7).size());
	}

	@Test
	public void testToString() {
		assertEquals("[[null],3,[[null],4,[[null],5,[[null],6,[[null],7,[null]]]]]]", toTree(3, 4, 5, 6, 7).toString());
		assertEquals("[[[[null],1,[null]],2,[[null],3,[null]]],4,[[[null],5,[null]],6,[[null],7,[null]]]]", toTree(4, 2, 1, 3, 6, 5, 7).toString());
	}

	private List<Integer> toTreeToList(Iterable<Integer> nums) {
		return toTree(nums).toList();
	}

	private BinarySearchTreeImpl<Integer> toTree(Integer... nums) {
		return toTree(Arrays.asList(nums));
	}

	private BinarySearchTreeImpl<Integer> toTree(Iterable<Integer> nums) {
		BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
		tree.add(nums);
		return tree;
	}

	@Test
	public void testAddingIsTailRecursive() {
		BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
		for (int i = 0; i < 10000; ++i) {
			tree.add(i);
		}
		// No StackOverflowError, thanks to tail recursive implementation
	}

	@Test(expected = StackOverflowError.class)
	public void testListingIsNotTailRecursive() {
		BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
		for (int i = 0; i < 10000; ++i) {
			tree.add(i);
		}
		assertEquals(11, tree.toList().size());
	}

	@Test
	public void testSizeIsNotTailRecursive() {
		int N = 10000;
		BinarySearchTreeImpl<Integer> tree = new BinarySearchTreeImpl<>();
		for (int i = 0; i < N; ++i) {
			tree.add(i);
		}
		assertEquals(N, tree.size());
	}
}
