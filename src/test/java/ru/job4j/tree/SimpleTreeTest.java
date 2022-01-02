package ru.job4j.tree;

import org.junit.Test;

import static org.junit.Assert.*;


public class SimpleTreeTest {
    @Test
    public void whenAdd2Elements() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
    }

    @Test
    public void whenFindAllAddedElements() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 4);
        tree.add(4, 5);
        assertTrue(tree.findBy(4).isPresent());
        assertTrue(tree.findBy(5).isPresent());
    }

    @Test
    public void whenNotFindAbsentElements() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 4);
        tree.add(4, 5);
        assertFalse(tree.findBy(6).isPresent());
    }

    @Test
    public void whenParentIsAbsentThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        assertFalse(tree.add(10, 5));
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 5);
        tree.add(5, 6);
        assertFalse(tree.add(2, 6));
    }

}