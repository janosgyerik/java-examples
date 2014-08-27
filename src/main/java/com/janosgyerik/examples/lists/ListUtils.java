package com.janosgyerik.examples.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListUtils {
	public static <T> List<List<T>> partition(Iterable<T> iterable, int size) {
		if (iterable == null) {
			return Collections.emptyList();
		}
		if (size < 1) {
			List<T> list = new ArrayList<>();
			for (T item : iterable) {
				list.add(item);
			}
			return Arrays.asList(list);
		}
		List<List<T>> result = new ArrayList<>();
		List<T> segment = new ArrayList<>(size);
		int i = 0;
		for (T item : iterable) {
			segment.add(item);
			if (++i == size) {
				result.add(segment);
				segment = new ArrayList<>(size);
				i = 0;
			}
		}
		if (i > 0) {
			result.add(segment);
		}
		return result;
	}
}
