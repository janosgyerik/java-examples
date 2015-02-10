package com.janosgyerik.examples.algorithm.sort;

public class MergeSort {

    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length);
    }

    protected static void mergeSort(int[] arr, int from, int to) {
        int half = from + (to - from) / 2;
        if (half - from > 1) {
            mergeSort(arr, from, half);
        }
        if (to - half > 1) {
            mergeSort(arr, half, to);
        }
        merge(arr, from, half, to);
    }

    protected static void merge(int[] arr, int from, int mid, int to) {
        int[] sorted = new int[to - from];
        for (int i = 0, pos1 = from, pos2 = mid; i < sorted.length; ++i) {
            if (pos1 < mid && pos2 < to) {
                if (arr[pos1] <= arr[pos2]) {
                    sorted[i] = arr[pos1++];
                } else {
                    sorted[i] = arr[pos2++];
                }
            } else if (pos1 < mid) {
                sorted[i] = arr[pos1++];
            } else {
                sorted[i] = arr[pos2++];
            }
        }
        System.arraycopy(sorted, 0, arr, from, sorted.length);
    }
}
