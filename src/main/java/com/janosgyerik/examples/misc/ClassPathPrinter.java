package com.janosgyerik.examples.misc;

public class ClassPathPrinter {
    public static void main(String[] args) {
        ClassPathFinder finder = new ClassPathFinder();
        for (String arg : args) {
            finder.printResourcePath(arg);
            finder.printClassPath(arg);
            finder.printJarPath(arg);
        }
    }
}
