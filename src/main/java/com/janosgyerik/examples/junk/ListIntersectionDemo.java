package com.janosgyerik.examples.junk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListIntersectionDemo {
    public static void main(String[] args) {
        List<String> orig = Arrays.asList("this", "is", "orig");
        List<String> second = Arrays.asList("this", "is", "second");
        Set<String> origSet = new HashSet<String>(orig);
        Set<String> secondSet = new HashSet<String>(second);
        origSet.removeAll(second);
        System.out.println("RESET" == "RESET");
        String key = "RESET";
        System.out.println(key == "RESET");
    }
}
