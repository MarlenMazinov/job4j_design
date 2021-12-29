package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(1);

    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (!contains(value)) {
            set.add(value);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < set.size(); i++) {
            if (value == null || value.equals(set.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}