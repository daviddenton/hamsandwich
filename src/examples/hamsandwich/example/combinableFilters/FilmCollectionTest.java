package hamsandwich.example.combinableFilters;

import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.junit.Test;

import static hamsandwich.example.combinableFilters.ActorMatchers.*;
import static hamsandwich.example.combinableFilters.MovieMatchers.*;
import static hamsandwich.hamcrest_to_junit_generics_mismatch.WildcardExtendsT.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;

public class FilmCollectionTest {
    private final FilmCollection filmCollection = new FilmCollection();

    @Test
    public void findAllRemakesMadeBefore1985() throws Exception {
        Matcher<Movie> remakesMadeBefore1985 = AllOf.allOf(isRemake(), yearProduced(is(lessThan(1985))));

        assertThat(filmCollection.find(remakesMadeBefore1985), allOf(hasItem(movieName(is(equalTo("Never Say Never Again"))))));
    }

    @Test
    public void findAllWithMaleAndFemaleCoStarsWhereTheSupportingActorIsUnknown() throws Exception {

        Matcher<Movie> maleAndFemaleCoStarsWhereTheSupportingActorIsUnknown = allOf(
                anyOf(
                        allOf(leadingActor(is(male())), supportingActor(is(female(originatingFrom(Origin.Unknown))))),
                        allOf(leadingActor(is(female())), supportingActor(is(male(originatingFrom(Origin.Unknown)))))
                )
        );

        assertThat(filmCollection.find(maleAndFemaleCoStarsWhereTheSupportingActorIsUnknown), allOf(
                hasItem(movieName(is(equalTo("Mission Impossible 2")))),
                hasItem(movieName(is(equalTo("The Accused"))))
        ));
    }

    @Test
    public void findAllBondMovies() throws Exception {
        Matcher<Iterable<? super String>> bondMovieTheme = hasItem(equalTo("Bond"));
        Matcher<Movie> bondMovies = AllOf.allOf(themes(bondMovieTheme));

        assertThat(filmCollection.find(bondMovies),
                allOf(
                        hasItem(movieName(is(equalTo("Thunderball")))),
                        hasItem(movieName(is(equalTo("The World Is Not Enough")))),
                        hasItem(movieName(is(equalTo("Never Say Never Again"))))
                )
        );
    }


}