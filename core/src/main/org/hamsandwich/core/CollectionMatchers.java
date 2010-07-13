package org.hamsandwich.core;

import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Convienience methods for common collection matching functions.
 */
public class CollectionMatchers {

    private CollectionMatchers() {
    }

    /**
     * Filter a list of subjects to just the entries that match the criteria
     * @param subjects the collection of subjects
     * @param valueMatchers the matchers to test against
     * @param <T> the type of the subject object
     * @return an iterable of matching subjects from the input collection
     */
    public static <T> Iterable<T> filter(Iterable<T> subjects, Matcher<? super T>... valueMatchers) {
        Matcher<T> combine = AllOf.allOf(valueMatchers);
        Set<T> matches = new HashSet<T>();
        for (T t : subjects) {
            if(combine.matches(t)) matches.add(t);
        }
        return matches;
    }

    /**
     * Filter a list of subjects to just the entries that match the criteria
     * @param subjects the collection of subjects
     * @param valueMatchers the matchers to test against
     * @param <T> the type of the subject object
     * @return an iterable of matching subjects from the input collection
     */
    public static <T> Iterable<T> filter(T[] subjects, Matcher<? super T>... valueMatchers) {
        return filter(Arrays.asList(subjects));
    }
}
