package com.janosgyerik.examples.misc;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class FlatIterator<E> implements Iterator<E> {
    private final Stack<Iterator<E>> stack = new Stack<>();
    private Iterator<E> current;
    private E nextItem = null;
    private boolean hasNextItem = false;
    private boolean advance = true;

    public FlatIterator(List<E> list) {
        current = list.iterator();
    }

    @Override
    public boolean hasNext() {
        if (advance) {
            advance = false;
            while (true) {
                if (current.hasNext()) {
                    E item = current.next();
                    if (item instanceof Iterable) {
                        stack.push(current);
                        current = ((Iterable<E>) item).iterator();
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
    public E next() {
        if (hasNext() && hasNextItem) {
            advance = true;
            return nextItem;
        }
        throw new NoSuchElementException();
    }
}
