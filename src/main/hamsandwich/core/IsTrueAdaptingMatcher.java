package hamsandwich.core;

/**
 * Convienience type. AdaptingMatcher which expected the output value to be "true".
 * Can be created with an optional name, otherwise uses the simple class name of the input type for description purposes.
 *
 * @param <I> The input type.
 */
public abstract class IsTrueAdaptingMatcher<I> extends BooleanAdaptingMatcher<I> {

    /**
     * Constructor. Uses the simple class name of the input type.
     */
    public IsTrueAdaptingMatcher() {
        super(true);
    }

    /**
     * Constructor. Uses the assigned name for description purposes.
     *
     * @param entityName the assigned name of the matched entity.
     */
    public IsTrueAdaptingMatcher(final String entityName) {
        super(entityName, true);
    }
}
