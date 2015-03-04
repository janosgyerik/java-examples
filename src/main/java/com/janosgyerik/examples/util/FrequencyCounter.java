package com.janosgyerik.examples.util;

import java.util.*;

public class FrequencyCounter<T> {

    private final Map<T, Integer> frequencyMap = new HashMap<>();

    private final Comparator<T> countComparator = (o1, o2) -> Integer.compare(frequencyMap.get(o1), frequencyMap.get(o2));

    public void add(T item) {
        Integer count = frequencyMap.get(item);
        if (count == null) {
            count = 0;
        }
        frequencyMap.put(item, count + 1);
    }

    public T getMostFrequentItem() {
        return toSortedMap().lastKey();
    }

    public int getMostFrequentCount() {
        SortedMap<T, Integer> sortedMap = toSortedMap();
        T lastKey = sortedMap.lastKey();
        return sortedMap.get(lastKey);
    }

    private SortedMap<T, Integer> toSortedMap(Comparator<T> comparator) {
        SortedMap<T, Integer> sortedMap = new TreeMap<>(comparator);
        sortedMap.putAll(frequencyMap);
        return sortedMap;
    }

    public SortedMap<T, Integer> toSortedMap() {
        return toSortedMap(countComparator);
    }

    public SortedMap<T, Integer> toReversedMap() {
        return toSortedMap(Collections.reverseOrder(countComparator));
    }

    public List<T> toSortedList(Comparator<T> comparator) {
        List<T> list = new ArrayList<>(frequencyMap.keySet());
        Collections.sort(list, comparator);
        return list;
    }

    public List<T> toSortedList() {
        return toSortedList(countComparator);
    }

    public List<T> toReversedList() {
        return toSortedList(Collections.reverseOrder(countComparator));
    }
}
