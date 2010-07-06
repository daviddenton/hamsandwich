package hamsandwich.core;

/**
 * Convienience type. AdaptingMatcher which expected the output value to be "false".
 * Can be created with an optional name, otherwise uses the simple class name of the input type for description purposes.
 *
 * @param <I> The input type.
 */
public abstract class IsFalseAdaptingMatcher<I> extends BooleanAdaptingMatcher<I> {

    /**
     * Constructor. Uses the simple class name of the input type.
     */
    public IsFalseAdaptingMatcher() {
        super(false);
    }

    /**
     * Constructor. Uses the assigned name for description purposes.
     *
     * @param entityName the assigned name of the matched entity.
     */

    public IsFalseAdaptingMatcher(final String entityName) {
        super(entityName, false);
    }
}