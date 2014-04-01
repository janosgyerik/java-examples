package com.janosgyerik.examples.junk;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringArrayDemo {
    private static final String[] DESEASE = new String[] { "Alcoholism",
            "Animal Desease", "Brain Desease", "Demantia", "Movement Disorder" };

    private static final String[] GENE = new String[] { "A1CF", "A2LD1", "A2M",
            "AA06", "AA1" };

    private static final String[] GEO = new String[] { "GSE14429", "GSE4226",
            "GSE8632", "GS9134", "GSE8641" };

    private static final String[] resultList;
    static {
        List<String> tmpList = new ArrayList<String>(Arrays.asList(DESEASE));
        tmpList.addAll(Arrays.asList(GENE));
        tmpList.addAll(Arrays.asList(GEO));
        resultList = tmpList.toArray(new String[tmpList.size()]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(StringArrayDemo.resultList));
    }
}
