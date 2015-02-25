package com.janosgyerik.examples.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public abstract class SortTest {

    private static final int MAX_LENGTH_TO_COMPARE_WITH_TOSTRING = 15;

    abstract void sort(int[] arr);

    private int[] newRandomArray(int num) {
        Random random = new Random(0);
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    private void sortAndVerify(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        sort(arr);

        if (arr.length < MAX_LENGTH_TO_COMPARE_WITH_TOSTRING) {
            assertEquals(Arrays.toString(copy), Arrays.toString(arr));
        } else {
            assertArrayEquals(copy, arr);
        }
    }

    @Test
    public void test_empty() {
        sortAndVerify(new int[0]);
    }

    @Test
    public void test_1() {
        sortAndVerify(new int[]{1});
    }

    @Test
    public void test_1_2() {
        sortAndVerify(new int[]{1, 2});
    }

    @Test
    public void test_2_1() {
        sortAndVerify(new int[]{2, 1});
    }

    @Test
    public void test_1_2_3() {
        sortAndVerify(new int[]{1, 2, 3});
    }

    @Test
    public void test_3_2_1() {
        sortAndVerify(new int[]{3, 2, 1});
    }

    @Test
    public void test_random_10() {
        sortAndVerify(newRandomArray(10));
    }

    @Test
    public void test_random_1000() {
        sortAndVerify(newRandomArray(1000));
    }

}

