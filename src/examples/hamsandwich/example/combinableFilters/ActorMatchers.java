package hamsandwich.example.combinableFilters;

import hamsandwich.core.AdaptingMatcher;
import hamsandwich.core.CannotAdaptException;
import hamsandwich.core.HamSandwichFactory;
import org.hamcrest.Matcher;

import static hamsandwich.core.ListUtils.join;
import static org.hamcrest.Matchers.*;

public class ActorMatchers {

    @HamSandwichFactory
    public static Matcher<Actor> male(Matcher<Actor>... valueMatchers) {
        return aGender(Gender.Male, valueMatchers);
    }

    @HamSandwichFactory
    public static Matcher<Actor> female(Matcher<Actor>... valueMatchers) {
        return aGender(Gender.Female, valueMatchers);
    }

    private static Matcher aGender(Gender gender, Matcher<Actor>[] valueMatchers) {
        return allOf(join(gender(gender), valueMatchers).toArray(new Matcher[0]));
    }

    @HamSandwichFactory
    private static Matcher<Actor> gender(Gender gender) {
        return new AdaptingMatcher<Actor, Gender>(is(equalTo(gender))) {
            @Override
            public Gender get(Actor in) throws CannotAdaptException {
                return in.gender;
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Actor> originatingFrom(Origin origin) {
        return new AdaptingMatcher<Actor, Origin>(is(equalTo(origin))) {
            @Override
            public Origin get(Actor in) throws CannotAdaptException {
                return in.origin;
            }
        };
    }
}
