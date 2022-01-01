package ru.job4j.map;

import java.util.Arrays;
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
        if (modCount >= capacity * LOAD_FACTOR) {
            expand();
            capacity = table.length;
        }
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null
                && !table[index].key.equals(key)) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        table = Arrays.copyOf(table, capacity * 2);
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = indexFor(hash(key.hashCode()));
        return table[index] == null || !table[index].key.equals(key)
                ? null : table[index].value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index].key.equals(key)) {
            table[index] = null;
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
                while (count < capacity && table[count] == null) {
                    count++;
                }
                return count < capacity;
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

    public static void main(String[] args) {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Petr");
        map.put(2, "Ivan");
        map.put(3, "Igor");
        map.put(4, "Fedor");
        map.put(5, "Marat");
        map.put(6, "Robert");
        map.put(7, "Inna");
        map.put(8, "Oleg");
        map.put(9, "Vasiliy");

        for (MapEntry<Integer, String> a : map.table) {
            if (a == null) {
                System.out.println("xxx");
                continue;
            }
            System.out.println(a.value);
            System.out.println(map.indexFor(map.hash(a.key.hashCode())));
        }

        System.out.println(map.capacity);
    }
}