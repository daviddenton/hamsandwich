package org.hamsandwich.core;

import org.hamcrest.Matcher;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.hamcrest.core.AllOf.allOf;

/**
 * A filtering object that can be used to search collections of objects
 *
 * @param <T> the type of the subject object
 */
public class CollectionFilter<T> {
    private final Matcher<T> combined;

    /**
     * Constructor.
     *
     * @param valueMatchers the matchers to test against
     */
    public CollectionFilter(Matcher<? super T>... valueMatchers) {
        combined = allOf(valueMatchers);
    }

    /**
     * Filter a list of subjects to just the entries that match the pre-defined criteria
     *
     * @param subjects the collection of subjects
     * @return an iterable of matching subjects from the input collection
     */
    public <T> Iterable<T> select(Iterable<T> subjects) {
        Set<T> matches = new HashSet<T>();
        for (T t : subjects) {
            if (combined.matches(t)) matches.add(t);
        }
        return matches;
    }

    /**
     * Filter a list of subjects to just the entries that do not match the pre-defined criteria
     *
     * @param subjects the collection of subjects
     * @return an iterable of matching subjects from the input collection
     */
    public <T> Iterable<T> remove(Iterable<T> subjects) {
        Set<T> matches = new HashSet<T>();
        for (T t : subjects) {
            if (!combined.matches(t)) matches.add(t);
        }
        return matches;
    }

    /**
     * Filter a list of subjects to just the entries that do not match the criteria
     *
     * @param subjects the collection of subjects
     * @param <T>      the type of the subject object
     * @return an iterable of matching subjects from the input collection
     */
    public <T> Iterable<T> remove(T[] subjects) {
        return remove(asList(subjects));
    }

    /**
     * Filter a list of subjects to just the entries that match the criteria
     *
     * @param subjects the collection of subjects
     * @param <T>      the type of the subject object
     * @return an iterable of matching subjects from the input collection
     */
    public <T> Iterable<T> select(T[] subjects) {
        return select(asList(subjects));
    }


    /**
     * Filter a list of subjects to just the entries that match the criteria
     *
     * @param subjects      the collection of subjects
     * @param valueMatchers the matchers to test against
     * @param <T>           the type of the subject object
     * @return an iterable of matching subjects from the input collection
     */
    public static <T> Iterable<T> select(Iterable<T> subjects, Matcher<? super T>... valueMatchers) {
        return new CollectionFilter<T>(valueMatchers).select(subjects);
    }

    /**
     * Filter a list of subjects to just the entries that match the criteria
     *
     * @param subjects      the collection of subjects
     * @param valueMatchers the matchers to test against
     * @param <T>           the type of the subject object
     * @return an iterable of matching subjects from the input collection
     */
    public static <T> Iterable<T> select(T[] subjects, Matcher<? super T>... valueMatchers) {
        return new CollectionFilter<T>(valueMatchers).select(subjects);
    }

    /**
     * Filter a list of subjects to just the entries that do not match the criteria
     *
     * @param subjects      the collection of subjects
     * @param valueMatchers the matchers to test against
     * @param <T>           the type of the subject object
     * @return an iterable of matching subjects from the input collection
     */
    public static <T> Iterable<T> remove(Iterable<T> subjects, Matcher<? super T>... valueMatchers) {
        return new CollectionFilter<T>(valueMatchers).remove(subjects);
    }

    /**
     * Filter a list of subjects to just the entries that do not match the criteria
     *
     * @param subjects      the collection of subjects
     * @param valueMatchers the matchers to test against
     * @param <T>           the type of the subject object
     * @return an iterable of matching subjects from the input collection
     */
    public static <T> Iterable<T> remove(T[] subjects, Matcher<? super T>... valueMatchers) {
        return new CollectionFilter<T>(valueMatchers).remove(subjects);
    }
}
