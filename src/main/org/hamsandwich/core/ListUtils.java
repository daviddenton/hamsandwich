package org.hamsandwich.core;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Utilities for manipulating lists.
 */
public class ListUtils {

    private ListUtils() {
    }

    /**
     * Convienience method. Returns a list containing the "tail" entries, with the "head" entry inserted at index 0.
     *
     * @param head the object to be at the top of the list.
     * @param tail the objects to be at the bottom of the list.
     * @param <T>  the input type.
     * @return a list containing both the head and tail entries.
     */
    public static <T> List<T> join(T head, T... tail) {
        List<T> list = new ArrayList<T>();
        list.add(head);
        list.addAll(asList(tail));
        return list;
    }
}
