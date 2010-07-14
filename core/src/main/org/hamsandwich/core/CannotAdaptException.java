package org.hamsandwich.core;

/**
 * Thrown when an object cannot be adapted to another type.
 */
public class CannotAdaptException extends Exception {

    public CannotAdaptException() {
        super("Cannot adapt for unknown reason");
    }

    public CannotAdaptException(String message) {
        super(message);
    }

}
