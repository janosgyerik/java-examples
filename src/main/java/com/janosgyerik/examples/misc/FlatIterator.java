package com.janosgyerik.examples.misc;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class FlatIterator implements Iterator<Object> {

    private final Stack<Iterator<?>> stack = new Stack<>();
    private Object nextItem;

    public FlatIterator(Iterable<?> iterable) {
        stack.push(iterable.iterator());
        moveToNext();
    }

    private void moveToNext() {
        do {
            Iterator<?> iter = stack.peek();
            if (iter.hasNext()) {
                Object item = iter.next();
                if (item instanceof Iterable) {
                    stack.push(((Iterable<?>) item).iterator());
                } else {
                    nextItem = item;
                    break;
                }
            } else {
                stack.pop();
            }
        } while (!stack.isEmpty());
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public Object next() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        try {
            return nextItem;
        } finally {
            moveToNext();
        }
    }
}