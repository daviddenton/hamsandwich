package org.hamsandwich.core;

/**
 * Adapts one value to another.
 *
 * @param <I> The input type.
 * @param <O> The output type.
 */
public interface Adapter<I, O> {

    /**
     * Adapts an input to an output.
     *
     * @param in the input value.
     * @return output the adapted output value.
     * @throws CannotAdaptException if the input could not be adapted to the output.
     */
    O get(I in) throws CannotAdaptException;
}
