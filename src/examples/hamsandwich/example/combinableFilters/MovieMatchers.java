package hamsandwich.example.combinableFilters;

import hamsandwich.core.AdaptingMatcher;
import hamsandwich.core.CannotAdaptException;
import hamsandwich.core.HamSandwichFactory;
import hamsandwich.core.IsTrueAdaptingMatcher;
import org.hamcrest.Matcher;

import java.util.Set;

public class MovieMatchers {

    @HamSandwichFactory
    public static Matcher<Movie> movieName(Matcher<? super String>... valueMatchers) {
        return new AdaptingMatcher<Movie, String>(valueMatchers) {
            @Override
            public String get(Movie in) throws CannotAdaptException {
                return in.title;
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Movie> themes(final Matcher<? super Set<String>> valueMatchers) {
        return new AdaptingMatcher<Movie, Set<String>>(valueMatchers) {
            @Override
            public Set<String> get(Movie in) throws CannotAdaptException {
                return in.themes;
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Movie> genre(Matcher<? super Genre>... valueMatchers) {
        return new AdaptingMatcher<Movie, Genre>(valueMatchers) {
            @Override
            public Genre get(Movie in) throws CannotAdaptException {
                return in.genre;
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Movie> isRemake() {
        return new IsTrueAdaptingMatcher<Movie>() {
            @Override
            public Boolean get(Movie in) throws CannotAdaptException {
                return in.remake;
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Movie> yearProduced(Matcher<? super Integer>... valueMatchers) {
        return new AdaptingMatcher<Movie, Integer>(valueMatchers) {
            @Override
            public Integer get(Movie in) throws CannotAdaptException {
                return in.year;
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Movie> leadingActor(Matcher<? super Actor>... valueMatchers) {
        return new AdaptingMatcher<Movie, Actor>(valueMatchers) {
            @Override
            public Actor get(Movie in) throws CannotAdaptException {
                return in.leadingActor;
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Movie> supportingActor(Matcher<? super Actor> valueMatchers) {
        return new AdaptingMatcher<Movie, Actor>(valueMatchers) {
            @Override
            public Actor get(Movie in) throws CannotAdaptException {
                return in.supportingActor;
            }
        };
    }


}
