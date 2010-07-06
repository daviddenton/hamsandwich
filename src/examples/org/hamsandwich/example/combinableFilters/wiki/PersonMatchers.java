package org.hamsandwich.example.combinableFilters.wiki;

import org.hamcrest.Matcher;
import org.hamsandwich.core.AdaptingMatcher;
import org.hamsandwich.core.CannotAdaptException;
import org.hamsandwich.core.HamSandwichFactory;

public class PersonMatchers {

    @HamSandwichFactory
    public static Matcher<Person> name(Matcher<? super String>... nameMatchers) {
        return new AdaptingMatcher<Person, String>(nameMatchers) {
            @Override
            public String get(Person in) throws CannotAdaptException {
                return in.name;
            }
        };
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
