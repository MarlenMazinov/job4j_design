package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T rsl = null;
        if (in.pop() == null) {
            throw new NoSuchElementException();
        }
        while(in.pop() != null) {
            out.push(in.pop());
            rsl = in.pop();
        }
        return rsl;
    }

    public void push(T value) {
    in.push(value);
    }
}