package com.janosgyerik.examples.misc;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class FlatIterator implements Iterator<Object> {
    private final Stack<Iterator<?>> stack = new Stack<>();
    private Iterator<?> current;
    private Object nextItem = null;
    private boolean hasNextItem = false;
    private boolean advance = true;

    public FlatIterator(List<?> list) {
        current = list.iterator();
    }

    @Override
    public boolean hasNext() {
        if (advance) {
            advance = false;
            while (true) {
                if (current.hasNext()) {
                    Object item = current.next();
                    if (item instanceof Iterable) {
                        stack.push(current);
                        current = ((Iterable<?>) item).iterator();
                    } else {
                        hasNextItem = true;
                        nextItem = item;
                        break;
                    }
                } else {
                    if (stack.empty()) {
                        hasNextItem = false;
                        break;
                    }
                    current = stack.pop();
                }
            }
        }
        return hasNextItem;
    }

    @Override
    public Object next() {
        if (hasNext() && hasNextItem) {
            advance = true;
            return nextItem;
        }
        throw new NoSuchElementException();
    }
}
