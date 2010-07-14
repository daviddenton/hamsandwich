package org.hamsandwich.example.combinableFilters;

import org.hamcrest.Matcher;
import org.hamsandwich.core.AdaptingMatcher;
import org.hamsandwich.core.CannotAdaptException;
import org.hamsandwich.core.HamSandwichFactory;

import static org.hamcrest.Matchers.*;
import static org.hamsandwich.core.ListUtils.join;
import static org.hamsandwich.core.ReplayMatcher.on;
import static org.hamsandwich.core.ReplayMatcher.replayMatcher;

public class ActorMatchers {

    @HamSandwichFactory
    public static Matcher<Actor> name(Matcher<String>... valueMatchers) {
        return replayMatcher(on(Actor.class).name(), valueMatchers);
    }

    @HamSandwichFactory
    public static Matcher<Actor> male(Matcher<Actor>... valueMatchers) {
        return aGender(Gender.Male, valueMatchers);
    }

    @HamSandwichFactory
    public static Matcher<Actor> female(Matcher<Actor>... valueMatchers) {
        return aGender(Gender.Female, valueMatchers);
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

    private static Matcher aGender(Gender gender, Matcher<Actor>[] valueMatchers) {
        return allOf(join(gender(gender), valueMatchers).toArray(new Matcher[0]));
    }
}
