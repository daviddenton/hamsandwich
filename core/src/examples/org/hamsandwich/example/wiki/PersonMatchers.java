package org.hamsandwich.example.wiki;

import org.hamcrest.Matcher;
import org.hamsandwich.core.AdaptingMatcher;
import org.hamsandwich.core.CannotAdaptException;
import org.hamsandwich.core.HamSandwichFactory;

import static org.hamsandwich.core.ReplayMatcher.on;
import static org.hamsandwich.core.ReplayMatcher.replayMatcher;

public class PersonMatchers {

    @HamSandwichFactory
    public static Matcher<Person> name(Matcher<String>... nameMatchers) {
        return replayMatcher(on(Person.class).getName(), nameMatchers);
    }

    @HamSandwichFactory
    public static Matcher<Person> age(Matcher<? super Integer>... ageMatchers) {
        return new AdaptingMatcher<Person, Integer>(ageMatchers) {
            @Override
            public Integer get(Person in) throws CannotAdaptException {
                return in.age;
            }
        };
    }
}
