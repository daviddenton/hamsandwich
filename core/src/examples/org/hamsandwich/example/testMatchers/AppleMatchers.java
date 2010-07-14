package org.hamsandwich.example.testMatchers;

import org.hamcrest.Matcher;
import org.hamsandwich.core.*;

import static org.hamsandwich.core.ReplayMatcher.on;

public class AppleMatchers {

    @HamSandwichFactory
    public static Matcher<Apple> colour(Matcher<Colour>... valueMatchers) {
        return ReplayMatcher.replayMatcher(on(Apple.class).colour(), valueMatchers);
    }

    @HamSandwichFactory
    public static Matcher<Apple> perfectlyRipe() {
        return new IsTrueAdaptingMatcher<Apple>() {
            @Override
            public Boolean get(Apple in) {
                return in.colour() == in.brand().perfectColour;
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Apple> brand(Matcher<Brand>... valueMatcher) {
        return new AdaptingMatcher<Apple, Brand>(valueMatcher) {
            @Override
            public Brand get(Apple in) {
                return in.brand();
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Apple> theSameColourAsAPerfectlyRipe(final Brand brand) {
        return new IsTrueAdaptingMatcher<Apple>() {
            @Override
            public Boolean get(Apple in) throws CannotAdaptException {
                return in.colour() == brand.perfectColour;
            }
        };
    }
}
