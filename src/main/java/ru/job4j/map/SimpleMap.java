package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (modCount == table.length) {
            throw new IndexOutOfBoundsException();
        }
        boolean rsl = false;
        Iterator<K> itr = iterator();
        if (table[indexFor(hash(key.hashCode()))] == null
                || table[indexFor(hash(key.hashCode()))].key.equals(key)) {
            table[indexFor(hash(key.hashCode()))] = new MapEntry<>(key, value);
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        if (modCount == table.length) {
            System.arraycopy(table, 0, table, 0, table.length - 1);
        }
    }

    @Override
    public V get(K key) {
        V rsl = null;
        return table[indexFor(hash(key.hashCode()))] == null
                ? null : table[indexFor(hash(key.hashCode()))].value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (table[indexFor(hash(key.hashCode()))].key.equals(key)) {
            table[indexFor(hash(key.hashCode()))] = null;
            modCount--;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[count++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}