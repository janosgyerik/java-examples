package com.janosgyerik.examples.lists;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ImmutableListTest {
    @Test
    public void testMutatedUnmodifiableList() {
        List<Integer> origList = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> mutableList = Collections.unmodifiableList(origList);
        origList.remove(new Integer(2));
        assertEquals(Arrays.asList(1, 3), mutableList);
    }
}
