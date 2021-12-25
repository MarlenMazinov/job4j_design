package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();
    private int counterIn = 0;
    private int counterOut = 0;

    public T poll() {
        T rsl;
        if (counterIn == 0) {
            throw new NoSuchElementException();
        }
        while (counterIn > 1) {
            out.push(in.pop());
            counterIn--;
            counterOut++;
        }
        rsl = in.pop();
        in = out;
        out = new SimpleStack<>();
        counterIn = counterOut;
        counterOut = 0;
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        counterIn++;
    }
}