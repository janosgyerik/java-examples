package com.janosgyerik.tools.algorithm.sort;

public class HeapSort {
    public static void sort(int[] arr) {
        heapify(arr);
        heapsort(arr);
    }

    protected static void heapify(int[] arr) {
        // TODO
    }

    private static void heapsort(int[] arr) {
        // TODO
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
