package org.hamsandwich.core;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.allOf;

/**
 * Factory methods for library matchers. Can be used as an alternative to extending the AdaptingMatcher classes,
 * depending on desired coding-style.
 */
public class HamSandwichMatchers {

    private HamSandwichMatchers() {
    }

    /**
     * A simple Hamcrest matcher which delegates a match attempt via an adapter to convert the input value.
     * Uses the simple class name of the input type for description purposes.
     *
     * @param adapter       used to convert the input object to the output type.
     * @param valueMatchers the delegate matcher for the output of the adaption.
     * @param <I>           The input type.
     * @param <O>           The output type.
     * @return the matcher
     */
    public static <I, O> Matcher<I> adaptingMatcher(final Adapter<I, O> adapter, Matcher<O>... valueMatchers) {
        return new TypeSafeAdaptingMatcher<I, O>(allOf(valueMatchers)) {
            @Override
            public O get(I in) throws CannotAdaptException {
                return adapter.get(in);
            }
        };
    }

    /**
     * A simple Hamcrest matcher which delegates a match attempt via an adapter to convert the input value.
     *
     * @param adapter       used to convert the input object to the output type.
     * @param entityName    the assigned name of the matched entity, used for description purposes.
     * @param valueMatchers the delegate matcher for the output of the adaption.
     * @param <I>           The input type.
     * @param <O>           The output type.
     * @return the matcher
     */
    public static <I, O> Matcher<I> adaptingMatcher(String entityName, final Adapter<I, O> adapter, Matcher<O>... valueMatchers) {
        return new TypeSafeAdaptingMatcher<I, O>(entityName, allOf(valueMatchers)) {
            @Override
            public O get(I in) throws CannotAdaptException {
                return adapter.get(in);
            }
        };
    }

    /**
     * AdaptingMatcher which expected the output value to be "true".
     *
     * @param entityName the assigned name of the matched entity, used for description purposes.
     * @param adapter    used to convert the input object to the output type.
     * @param <I>        The input type.
     */
    public static <I> Matcher<I> isTrueAdaptingMatcher(String entityName, final Adapter<I, Boolean> adapter) {
        return new IsTrueAdaptingMatcher<I>(entityName) {
            @Override
            public Boolean get(I in) throws CannotAdaptException {
                return adapter.get(in);
            }
        };
    }

    /**
     * AdaptingMatcher which expected the output value to be "true".
     * Uses the simple class name of the input type for description purposes.
     *
     * @param adapter used to convert the input object to the output type.
     * @param <I>     The input type.
     */
    public static <I> Matcher<I> isTrueAdaptingMatcher(final Adapter<I, Boolean> adapter) {
        return new IsTrueAdaptingMatcher<I>() {
            @Override
            public Boolean get(I in) throws CannotAdaptException {
                return adapter.get(in);
            }
        };
    }

    /**
     * AdaptingMatcher which expected the output value to be "false".
     *
     * @param entityName the assigned name of the matched entity, used for description purposes.
     * @param adapter    used to convert the input object to the output type.
     * @param <I>        The input type.
     */
    public static <I> Matcher<I> isFalseAdaptingMatcher(String entityName, final Adapter<I, Boolean> adapter) {
        return new IsFalseAdaptingMatcher<I>(entityName) {
            @Override
            public Boolean get(I in) throws CannotAdaptException {
                return adapter.get(in);
            }
        };
    }

    /**
     * AdaptingMatcher which expected the output value to be "true".
     * Uses the simple class name of the input type for description purposes.
     *
     * @param adapter used to convert the input object to the output type.
     * @param <I>     The input type.
     */
    public static <I> Matcher<I> isFalseAdaptingMatcher(final Adapter<I, Boolean> adapter) {
        return new IsFalseAdaptingMatcher<I>() {
            @Override
            public Boolean get(I in) throws CannotAdaptException {
                return adapter.get(in);
            }
        };
    }
}
