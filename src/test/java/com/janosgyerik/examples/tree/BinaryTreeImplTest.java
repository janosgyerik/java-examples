package com.janosgyerik.examples.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class BinaryTreeImplTest {
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

	private List<Integer> toTreeToList(Integer... nums) {
		return toTreeToList(Arrays.asList(nums));
	}

	private List<Integer> toTreeToList(Iterable<Integer> nums) {
		return toTree(nums).toList();
	}

	private BinaryTreeImpl<Integer> toTree(Integer... nums) {
		return toTree(Arrays.asList(nums));
	}

	private BinaryTreeImpl<Integer> toTree(Iterable<Integer> nums) {
		BinaryTreeImpl<Integer> tree = new BinaryTreeImpl<>();
		tree.add(nums);
		return tree;
	}
}
