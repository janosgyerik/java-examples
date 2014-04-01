package com.janosgyerik.examples.junk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitDemo {
    public static void main(String[] args) {
//        List<String> ops = new ArrayList<String>(Arrays.asList("9+3*2/1".split("[0-9]")));
//        ops.remove(0);
//        System.out.println(ops);
//        System.out.println(Arrays.asList("9+3*2/1".split("[0-9]", -1)));

        String setExpression = "9+3*2/1";

        String[] numbers = setExpression.split("[*/+-]");
        String[] ops = setExpression.split("[123456789]");

        ArrayList <String> setNumbers = new ArrayList <String>();
        ArrayList <String> setOps = new ArrayList <String>();


        for(int i=0; i<numbers.length; i++){
            setNumbers.add(numbers[i]);
        }

        for (int i = 1; i < ops.length; i++) {
            setOps.add(ops[i]);
        }

        System.out.println(setNumbers);
        System.out.println(setOps);
    }
}
