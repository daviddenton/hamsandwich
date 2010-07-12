package org.hamsandwich.core;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Convienience type. AdaptingMatcher which expected the output value to be equal() to the test value.
 * Can be created with an optional name, otherwise uses the simple class name of the input type for description purposes.
 *
 * @param <I> The input type.
 */
public abstract class IsEqualAdaptingMatcher<I, O> extends AdaptingMatcher<I, O> {

    /**
     * Constructor. Uses the simple class name of the input type.
     */
    public IsEqualAdaptingMatcher(O testValue) {
        super(is(equalTo(testValue)));
    }

    /**
     * Constructor. Uses the assigned name for description purposes.
     *
     * @param entityName the assigned name of the matched entity.
     * @param testValue  the object to test for equality against.
     */

    public IsEqualAdaptingMatcher(final String entityName, O testValue) {
        super(entityName, is(equalTo(testValue)));
    }
}