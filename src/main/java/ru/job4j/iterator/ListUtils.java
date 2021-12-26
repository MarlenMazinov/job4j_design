package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> listItr = list.listIterator();
        while (listItr.hasNext()) {
            if (listItr.nextIndex() == index) {
                listItr.add(value);
                break;
            }
            listItr.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> listItr = list.listIterator();
        while (listItr.hasNext()) {
            if (listItr.nextIndex() == index) {
                listItr.next();
                listItr.add(value);
                break;
            }
            listItr.next();
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> listItr = list.listIterator();
        while (listItr.hasNext()) {
            if (filter.test(listItr.next())) {
                listItr.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> listItr = list.listIterator();
        while (listItr.hasNext()) {
            if (filter.test(listItr.next())) {
                listItr.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> listItr = list.listIterator();
        ListIterator<T> elementsItr = elements.listIterator();
        while (elementsItr.hasNext()) {
            T elem = elementsItr.next();
            while (listItr.hasNext()) {
                if (elem.equals(listItr.next())) {
                    listItr.remove();
                }
            }
            listItr = list.listIterator();
        }
    }

}