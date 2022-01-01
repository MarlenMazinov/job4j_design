package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenAddThreeElement() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "Petr"));
        assertTrue(map.put(2, "Ivan"));
        assertTrue(map.put(3, "Igor"));
    }

    @Test
    public void whenCellNotEmpty() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "Petr");
        assertFalse(map.put(1, "Elena"));
    }

    @Test
    public void whenAddInLastFreeCell() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "Petr");
        map.put(2, "Ivan");
        map.put(3, "Igor");
        map.put(4, "Fedor");
        map.put(5, "Marat");
        map.put(6, "Robert");
        map.put(7, "Inna");
        assertTrue(map.put(8, "Mary"));
    }

    @Test
    public void whenAddInFullTable() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "Petr");
        map.put(2, "Ivan");
        map.put(3, "Igor");
        map.put(4, "Fedor");
        map.put(5, "Marat");
        map.put(6, "Robert");
        map.put(7, "Inna");
        map.put(8, "Oleg");
        map.put(9, "Vasiliy");
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(map.get(i));
        }
        boolean rsl = false;
        for (int i = 1; i < 10; i++) {
            rsl = list.contains(map.get(i));
        }
        assertTrue(rsl);
    }

    @Test
    public void whenGetOneElement() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "Petr");
        assertThat(map.get(1), is("Petr"));
    }

    @Test
    public void whenGetAllAddedElements() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "Petr");
        map.put(2, "Ivan");
        map.put(3, "Igor");
        String[] expected = {map.get(1), map.get(2), map.get(3)};
        assertTrue(Arrays.asList(expected).contains(map.get(1)));
        assertTrue(Arrays.asList(expected).contains(map.get(2)));
        assertTrue(Arrays.asList(expected).contains(map.get(3)));
    }

    @Test
    public void whenGetAbsentElement() {
        Map<Integer, String> map = new SimpleMap<>();
        assertNull(map.get(1));
    }

    @Test
    public void whenRemoveElement() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "Petr");
        assertTrue(map.remove(1));
    }

    @Test
    public void whenRemovedElementAbsent() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "Petr");
        map.put(2, "Ivan");
        map.put(3, "Igor");
        map.remove(1);
        assertNull(map.get(1));
    }

    @Test
    public void whenIteratorPassEmptyCells() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "Petr");
        map.put(2, "Ivan");
        map.put(3, "Igor");
        Iterator<Integer> itr = map.iterator();
        int count = 0;
        while (itr.hasNext()) {
            itr.next();
            count++;
        }
        assertThat(count, is(3));
    }
}