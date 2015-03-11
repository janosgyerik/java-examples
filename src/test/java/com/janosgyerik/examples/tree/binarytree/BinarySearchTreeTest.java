package com.janosgyerik.examples.tree.binarytree;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public abstract class BinarySearchTreeTest {

    protected abstract <T extends Comparable<T>> BinarySearchTree<T> newBinarySearchTree();

    @Test
    public void test_insert_contains() {
        BinarySearchTree<Integer> tree = newBinarySearchTree();
        int val = 3;
        assertFalse(tree.contains(val));
        tree.insert(val);
        assertTrue(tree.contains(val));
        assertFalse(tree.contains(val + 1));
    }

    private List<Integer> createList(int num) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= num; ++i) {
            list.add(i);
        }
        return list;
    }

    @Test
    public void test_bst_from_sorted_list() {
        int num = 10;
        List<Integer> list = createList(num);

        BinarySearchTree<Integer> tree = newBinarySearchTree();
        list.forEach(tree::insert);

        assertTrue(TreeUtils.isBinarySearchTree(tree.getRoot(), 0, num + 1));
    }

    @Test
    public void test_bst_from_shuffled_list() {
        int num = 10;
        List<Integer> list = createList(num);
        Collections.shuffle(list, new Random(1));

        BinarySearchTree<Integer> tree = newBinarySearchTree();
        list.forEach(tree::insert);

        assertTrue(TreeUtils.isBinarySearchTree(tree.getRoot(), 0, num + 1));
    }

    @Test
    public void test_sorted_inorder() {
        int num = 10;
        List<Integer> list = createList(num);
        List<Integer> shuffled = new ArrayList<>(list);
        Collections.shuffle(shuffled, new Random(1));
        assertNotEquals(list, shuffled);

        BinarySearchTree<Integer> tree = newBinarySearchTree();
        list.forEach(tree::insert);

        Iterator<Integer> treeIterator = Iterators.inOrderIterator(tree.getRoot());
        Iterator<Integer> listIterator = list.listIterator();
        while (treeIterator.hasNext() && listIterator.hasNext()) {
            assertEquals(listIterator.next(), treeIterator.next());
        }
        assertFalse(treeIterator.hasNext());
        assertFalse(listIterator.hasNext());
    }
}