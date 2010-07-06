package hamsandwich.example.combinableFilters.wiki;

import hamsandwich.core.AdaptingMatcher;
import hamsandwich.core.CannotAdaptException;
import hamsandwich.core.HamSandwichFactory;
import org.hamcrest.Matcher;

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
