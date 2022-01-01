package ru.job4j.map;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenAddThreeElement() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "Petr");
        assertTrue(map.put(1, "Petr"));
        assertTrue(map.put(2, "Ivan"));
        assertTrue(map.put(3, "Igor"));
    }

    @Test
    public void whenUpdateElement() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "Petr");
        assertTrue(map.put(1, "Elena"));
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

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddInFullTable() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "Petr");
        map.put(2, "Ivan");
        map.put(3, "Igor");
        map.put(4, "Fedor");
        map.put(5, "Marat");
        map.put(6, "Robert");
        map.put(7, "Inna");
        map.put(8, "Mary");
        map.put(9, "Petr");
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
}