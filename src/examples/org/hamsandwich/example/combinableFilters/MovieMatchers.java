package org.hamsandwich.example.combinableFilters;

import org.hamcrest.Matcher;
import org.hamsandwich.core.CannotAdaptException;
import org.hamsandwich.core.HamSandwichFactory;
import org.hamsandwich.core.IsTrueAdaptingMatcher;
import org.hamsandwich.core.TypeSafeAdaptingMatcher;

import java.util.Set;

public class MovieMatchers {

    @HamSandwichFactory
    public static Matcher<Movie> movieName(Matcher<? super String>... valueMatchers) {
        return new TypeSafeAdaptingMatcher<Movie, String>(valueMatchers) {
            @Override
            public String get(Movie in) throws CannotAdaptException {
                return in.title;
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Movie> themes(final Matcher<? super Set<String>> valueMatchers) {
        return new TypeSafeAdaptingMatcher<Movie, Set<String>>(valueMatchers) {
            @Override
            public Set<String> get(Movie in) throws CannotAdaptException {
                return in.themes;
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Movie> genre(Matcher<? super Genre>... valueMatchers) {
        return new TypeSafeAdaptingMatcher<Movie, Genre>(valueMatchers) {
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
        return new TypeSafeAdaptingMatcher<Movie, Integer>(valueMatchers) {
            @Override
            public Integer get(Movie in) throws CannotAdaptException {
                return in.year;
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Movie> leadingActor(Matcher<? super Actor>... valueMatchers) {
        return new TypeSafeAdaptingMatcher<Movie, Actor>(valueMatchers) {
            @Override
            public Actor get(Movie in) throws CannotAdaptException {
                return in.leadingActor;
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<Movie> supportingActor(Matcher<? super Actor> valueMatchers) {
        return new TypeSafeAdaptingMatcher<Movie, Actor>(valueMatchers) {
            @Override
            public Actor get(Movie in) throws CannotAdaptException {
                return in.supportingActor;
            }
        };
    }


}
