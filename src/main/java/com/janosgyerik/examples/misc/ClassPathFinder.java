package com.janosgyerik.examples.misc;


import java.net.URL;

public class ClassPathFinder {

    public URL getResourceUrl(String resourceName) {
        return getClass().getClassLoader().getResource(resourceName);
    }

    private Class<? extends Object> getClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    public URL getClassUrl(String className) throws ClassNotFoundException {
        Class<? extends Object> klass = getClass(className);
        return klass.getResource("/" + klass.getName().replace(".", "/") + ".class");
    }

    public URL getJarUrl(String className) throws ClassNotFoundException {
        Class<? extends Object> klass = getClass(className);
        return klass.getProtectionDomain().getCodeSource().getLocation();
    }

    public void printResourcePath(String resourceName) {
        URL url = getResourceUrl(resourceName);
        System.out.println("resource: " + (url != null ? url : noSuchResourceMessage(resourceName)));
    }

    public void printClassPath(String className) {
        System.out.println("class: " + getClassUrlAsString(className));
    }

    private String getClassUrlAsString(String className) {
        try {
            return getClassUrl(className).toString();
        } catch (ClassNotFoundException e) {
            return noSuchClassMessage(className);
        }
    }

    public void printJarPath(String className) {
        System.out.println("jar: " + getJarUrlAsString(className));
    }

    private String getJarUrlAsString(String className) {
        try {
            return getJarUrl(className).toString();
        } catch (ClassNotFoundException e) {
            return noSuchClassMessage(className);
        }
    }

    private String noSuchClassMessage(String className) {
        return String.format("null (no such class: %s)", className);
    }

    private String noSuchResourceMessage(String resourceName) {
        return String.format("null (no such resource: %s)", resourceName);
    }
}
