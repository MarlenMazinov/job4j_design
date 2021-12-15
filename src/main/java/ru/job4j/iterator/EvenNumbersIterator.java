package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private Integer indexHn = 0;
    private Integer indexN = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public boolean hasNextLocal(String index) {
        if (index.equals("indexHn")) {
            while (indexHn < data.length && data[indexHn] % 2 != 0) {
                indexHn++;
            }
            return indexHn++ < data.length;
        } else {
            while (indexN < data.length && data[indexN] % 2 != 0) {
                indexN++;
            }
            return indexN < data.length;
        }
    }

    @Override
    public boolean hasNext() {
        return hasNextLocal("indexHn");
    }

    @Override
    public Integer next() {
        if (!hasNextLocal("indexN")) {
            throw new NoSuchElementException();
        }
        return data[indexN++];

    }
}
