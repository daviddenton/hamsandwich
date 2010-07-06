package hamsandwich.core;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marks a static factory method so it can be recognised as a named matcher
 * A factory method is an equivalent to a named constructor.
 */
@Retention(RUNTIME)
@Target({METHOD})
public @interface HamSandwichFactory {
}
