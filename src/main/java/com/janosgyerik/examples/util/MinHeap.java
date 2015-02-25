package com.janosgyerik.examples.util;

import java.util.Arrays;

public class MinHeap {

    private int[] storage;

    private int size;
    public MinHeap(int capacity) {
        storage = new int[capacity];
        size = 0;
    }

    public static MinHeap fromArray(int[] arr) {
        MinHeap heap = new MinHeap(arr.length);
        for (int num : arr) {
            heap.insert(num);
        }
        return heap;
    }

    public int[] toArray() {
        int[] copy = new int[size];
        System.arraycopy(storage, 0, copy, 0, size);
        return copy;
    }

    private int getParentIndex(int index) {
        return (index - 2 + index % 2) / 2;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private void insert(int num) {
        ensureCapacity(size + 1);
        storage[size++] = num;

        int currentIndex = size - 1;
        while (smallerThanParent(currentIndex)) {
            currentIndex = swapWithParent(currentIndex);
        }
    }

    public int removeTop() {
        int top = storage[0];

        int currentIndex = 0;
        while (hasChildren(currentIndex)) {
            currentIndex = swapWithMinChild(currentIndex);
        }

        if (--size <= currentIndex) {
            return top;
        }

        storage[currentIndex] = storage[size];
        while (smallerThanParent(currentIndex)) {
            currentIndex = swapWithParent(currentIndex);
        }

        return top;
    }

    private boolean smallerThanParent(int index) {
        return index > 0 && storage[index] < storage[getParentIndex(index)];
    }

    private boolean hasChildren(int index) {
        return getLeftChildIndex(index) < size;
    }

    private int swapWithParent(int index) {
        int parentIndex = getParentIndex(index);
        swapIndexes(index, parentIndex);
        return parentIndex;
    }

    private int swapWithMinChild(int index) {
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);
        int minChildIndex;

        if (rightChildIndex < size) {
            if (storage[leftChildIndex] < storage[rightChildIndex]) {
                minChildIndex = leftChildIndex;
            } else {
                minChildIndex = rightChildIndex;
            }
        } else {
            minChildIndex = leftChildIndex;
        }

        swapIndexes(index, minChildIndex);

        return minChildIndex;
    }

    private void swapIndexes(int index1, int index2) {
        int work = storage[index1];
        storage[index1] = storage[index2];
        storage[index2] = work;
    }

    private void ensureCapacity(int targetSize) {
        if (targetSize >= storage.length) {
            int[] newStorage = new int[storage.length * 2];
            System.arraycopy(storage, 0, newStorage, 0, size);
            storage = newStorage;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
