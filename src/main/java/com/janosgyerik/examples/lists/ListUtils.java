package com.janosgyerik.examples.lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtils {
	public static <T> List<List<T>> partition(List<T> orig, int size) {
		if (size < 1) {
			throw new IllegalArgumentException("The target partition size must be 1 or greater");
		}
		if (orig == null) {
			return Collections.emptyList();
		}
		int origSize = orig.size();
		List<List<T>> result = new ArrayList<>(origSize / size + 1);
		for (int i = 0; i < origSize; i += size) {
			result.add(orig.subList(i, Math.min(i + size, origSize)));
		}
		return result;
	}
}
