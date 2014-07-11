package com.janosgyerik.examples.misc;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class FlatIterator<E> implements Iterator<E> {

    private final Stack<Iterator<?>> stack = new Stack<>();
    private E nextItem;

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
                    //noinspection unchecked
                    nextItem = (E) item;
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

    public E next() {
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