package com.janosgyerik.examples.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

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

	private List<Integer> toTreeToList(Integer... nums) {
		return toTreeToList(Arrays.asList(nums));
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
}
