package hamsandwich.example.combinableFilters.testMatchers;

import hamsandwich.core.AdaptingMatcher;
import hamsandwich.core.CannotAdaptException;
import hamsandwich.core.HamSandwichFactory;
import hamsandwich.core.IsTrueAdaptingMatcher;
import org.hamcrest.Matcher;

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
