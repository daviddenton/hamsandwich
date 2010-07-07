package org.hamsandwich.core;

import org.hamcrest.Matcher;

/**
 * A simple Hamcrest matcher which delegates a match attempt via an adapter method to convert the input value.
 * Can be created with an optional name, otherwise uses the simple class name of the input type for description purposes.
 * Using this class is not recommended (use TypeSafeAdaptingMatcher instead) unless you get into trouble with/are scared of
 * Java's broken Generics system
 *
 * @param <I> The input type.
 */
public abstract class AdaptingMatcher<I> extends TypeSafeAdaptingMatcher<I, Object> {

    /**
     * Constructor - call from a factory method annotated with "@HamSandwichFactory". Uses the simple class name of the input type for description purposes.
     *
     * @param valueMatchers the delegated matchers for the output of the adaption.
     */
    public AdaptingMatcher(Matcher<?>... valueMatchers) {
        super((Matcher<? super Object>[]) valueMatchers);
    }

    /**
     * Constructor. Uses the assigned name for description purposes.
     *
     * @param entityName    the assigned name of the matched entity, used for description purposes.
     * @param valueMatchers the delegated matchers for the output of the adaption.
     */
    public AdaptingMatcher(String entityName, Matcher<?>... valueMatchers) {
        super(entityName, (Matcher<? super Object>[]) valueMatchers);
    }
}
