package com.janosgyerik.examples.junk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AutoboxingFunDemo {
    public static void main(String[] args) throws IOException {
        HashMap<Long,LinkedList<Long>> pMap = new HashMap<Long,LinkedList<Long>>();

        String file = "/tmp/sample.txt";
        BufferedReader br = new BufferedReader(new FileReader(file));

        String newLine;
        long lineNum = 0;
        while((newLine = br.readLine()) != null) {
            String[] tempStr = newLine.split(" ");
            Long address = Long.parseLong(tempStr[0],16);

            if(!pMap.containsKey(address)) {
                LinkedList<Long> tempList = new LinkedList<Long>();
                tempList.add(lineNum);
                pMap.put(address,tempList);
            }
            else {
                pMap.get(address).add(lineNum);
            }

            lineNum++;
        }
        for (Map.Entry<Long, LinkedList<Long>> entry : pMap.entrySet()) {
            System.out.println(entry.getKey());
            for (Long val : entry.getValue()) {
                System.out.println("  " + val);
            }
        }
    }
}
