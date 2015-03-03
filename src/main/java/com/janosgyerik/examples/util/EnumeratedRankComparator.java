package com.janosgyerik.examples.util;

import java.util.*;

public class EnumeratedRankComparator<T> implements Comparator<T> {
    private final Map<T, Integer> itemsToIndex;

    private EnumeratedRankComparator(List<T> items) {
        itemsToIndex = new HashMap<>(items.size());

        int index = 0;
        for (T item : items) {
            if (itemsToIndex.containsKey(item)) {
                throw new IllegalArgumentException("Inconsistent ranks: there should be no duplicates");
            }
            itemsToIndex.put(item, ++index);
        }
    }

    public static <T> EnumeratedRankComparator<T> fromLowToHigh(List<T> items) {
        return new EnumeratedRankComparator<T>(items);
    }

    public static <T> EnumeratedRankComparator<T> fromHighToLow(List<T> items) {
        List<T> copy = new ArrayList<>(items);
        Collections.reverse(copy);
        return new EnumeratedRankComparator<T>(copy);
    }

    @Override
    public int compare(T o1, T o2) {
        return Integer.compare(itemsToIndex.get(o1), itemsToIndex.get(o2));
    }
}
