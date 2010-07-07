package org.hamsandwich.core;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.internal.matchers.TypeSafeMatcher;

import static org.hamcrest.Matchers.allOf;

/**
 * A simple TypeSafe Hamcrest matcher which delegates a match attempt via an adapter method to convert the input value.
 * Can be created with an optional name, otherwise uses the simple class name of the input type for description purposes.
 * If you get into trouble with Java's broken Generics system then you can simply use AdaptingMatcher instead.
 *
 * @param <I> The input type.
 * @param <O> The output type.
 */
public abstract class TypeSafeAdaptingMatcher<I, O> extends TypeSafeMatcher<I> implements Adapter<I, O> {

    private final String condition;
    private final Matcher<? super O> valueMatcher;
    private final NameResolver entityNameResolver;

    /**
     * Constructor - call from a factory method annotated with "@HamSandwichFactory". Uses the simple class name of the input type for description purposes.
     *
     * @param valueMatchers the delegated matchers for the output of the adaption.
     */
    public TypeSafeAdaptingMatcher(Matcher<? super O>... valueMatchers) {
        this(new GenerifiedClassEntityNameResolver(), new StackInterrogatingNameResolver(), valueMatchers);
    }

    /**
     * Constructor. Uses the assigned name for description purposes.
     *
     * @param entityName    the assigned name of the matched entity, used for description purposes.
     * @param valueMatchers the delegated matchers for the output of the adaption.
     */
    public TypeSafeAdaptingMatcher(String entityName, Matcher<? super O>... valueMatchers) {
        this(new StaticNameResolver(entityName), new StackInterrogatingNameResolver(), valueMatchers);
    }

    private TypeSafeAdaptingMatcher(NameResolver entityNameResolver, NameResolver conditionNameResolver, Matcher<? super O>... valueMatchers) {
        this.valueMatcher = allOf(valueMatchers);
        this.condition = conditionNameResolver.resolveFor(this);
        this.entityNameResolver = entityNameResolver;
    }

    @Override
    public boolean matchesSafely(I in) {
        try {
            return valueMatcher.matches(get(in));
        } catch (ClassCastException classCastException) {
            return false;
        } catch (CannotAdaptException cannotAdaptException) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("[");
        description.appendText(entityNameResolver.resolveFor(this) + " where ");
        description.appendText(condition);
        description.appendText(" ");
        valueMatcher.describeTo(description);
        description.appendText("]");
    }

    @Override
    public void describeMismatch(Object o, Description description) {
        if (o != null && ReflectionUtils.getGenerifiedClassesOf(this).get(0) == o.getClass()) {
            try {
                valueMatcher.describeMismatch(get((I) o), description);
                return;
            } catch (CannotAdaptException cannotAdaptException) {
                // fall through
            }
        }
        super.describeMismatch(o, description);
    }

    @Override
    public String toString() {
        StringDescription description = new StringDescription();
        this.describeTo(description);
        return description.toString();
    }
}
