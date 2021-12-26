package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int counterIn = 0;
    private int counterOut = 0;

    public T poll() {
        T rsl;
        if (counterIn == 0) {
            throw new NoSuchElementException();
        }
        while (counterIn > 0) {
            out.push(in.pop());
            counterIn--;
            counterOut++;
        }
        rsl = out.pop();
        counterOut--;
        while (counterOut > 0) {
            in.push(out.pop());
            counterOut--;
            counterIn++;
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        counterIn++;
    }
}