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

    public SortedMap<T, Integer> toSortedMap() {
        SortedMap<T, Integer> sortedMap = new TreeMap<>(countComparator);
        sortedMap.putAll(frequencyMap);
        return sortedMap;
    }

    public SortedMap<T, Integer> toReversedMap() {
        SortedMap<T, Integer> sortedMap = new TreeMap<>(Collections.reverseOrder(countComparator));
        sortedMap.putAll(frequencyMap);
        return sortedMap;
    }

    public List<T> toSortedList() {
        List<T> list = new ArrayList<>(frequencyMap.keySet());
        Collections.sort(list, countComparator);
        return list;
    }

    public List<T> toReversedList() {
        List<T> list = new ArrayList<>(frequencyMap.keySet());
        Collections.sort(list, Collections.reverseOrder(countComparator));
        return list;
    }
}
