package hamsandwich.core;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Convienience type. AdaptingMatcher which can used to compare only boolean matches.
 * Can be created with an optional name, otherwise uses the simple class name of the input type for description purposes.
 *
 * @param <I> The input type.
 */
public abstract class BooleanAdaptingMatcher<I> extends AdaptingMatcher<I, Boolean> {

    /**
     * Constructor. Uses the simple class name of the input type.
     *
     * @param expected the expected result of the match operation.
     */
    public BooleanAdaptingMatcher(boolean expected) {
        super(is(expected));
    }

    /**
     * Constructor. Uses the assigned name for description purposes.
     *
     * @param entityName the assigned name of the matched entity.
     * @param expected   the expected result of the match operation.
     */
    public BooleanAdaptingMatcher(String entityName, boolean expected) {
        super(entityName, is(equalTo(expected)));
    }
}