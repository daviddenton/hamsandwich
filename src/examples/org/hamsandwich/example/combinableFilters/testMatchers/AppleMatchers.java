package org.hamsandwich.example.combinableFilters.testMatchers;

import org.hamcrest.Matcher;
import org.hamsandwich.core.AdaptingMatcher;
import org.hamsandwich.core.CannotAdaptException;
import org.hamsandwich.core.HamSandwichFactory;
import org.hamsandwich.core.IsTrueAdaptingMatcher;

public class AppleMatchers {

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
    public static Matcher<Apple> brand(Matcher<? super Brand>... valueMatcher) {
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
