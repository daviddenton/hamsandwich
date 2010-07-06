package hamsandwich.example.combinableFilters;

import org.hamcrest.Matcher;

import java.util.HashSet;
import java.util.Set;

import static hamsandwich.example.combinableFilters.Actor.actor;
import static hamsandwich.example.combinableFilters.Gender.Female;
import static hamsandwich.example.combinableFilters.Gender.Male;
import static hamsandwich.example.combinableFilters.Genre.*;
import static hamsandwich.example.combinableFilters.MovieBuilder.aMovieCalled;
import static hamsandwich.example.combinableFilters.Origin.*;

public class FilmCollection {
    private static final Set<Movie> COLLECTION = new HashSet<Movie>() {{
        add(aMovieCalled("Batman").a(Action).remake().from(1989).starring(actor("Michael Keaton", Male, Film)).and(actor("Jack Nicholson", Male, Film)).withThemes("Crime"));
        add(aMovieCalled("BMX Bandits").a(Action).from(1983).starring(actor("David Argue", Male, Film)).and(actor("Nicole Kidman", Female, Unknown)).withThemes("Crime"));
        add(aMovieCalled("Charade").a(Thriller).from(1963).starring(actor("Audrey Hepburn", Female, Film)).and(actor("Cary Grant", Male, Film)).withThemes("Espionage"));
        add(aMovieCalled("Eyes Wide Shut").a(Action).from(1999).starring(actor("Tom Cruise", Male, Film)).and(actor("Nicole Kidman", Male, Film)).withThemes("New York", "Sex"));
        add(aMovieCalled("Fight Club").a(Thriller).from(1999).starring(actor("Ed Norton", Male, Film)).and(actor("Brad Pitt", Male, Film)).withThemes("Madness", "Anarchy"));
        add(aMovieCalled("Indiana Jones iii").a(Action).from(1989).starring(actor("Harrison Ford", Male, Film)).and(actor("Sean Connery", Male, Film)).withThemes("War", "Religion"));
        add(aMovieCalled("LA Confidential").a(Thriller).from(1997).starring(actor("Guy Pearce", Male, Tv)).and(actor("Kim Basinger", Female, Film)).withThemes("Crime", "Police"));
        add(aMovieCalled("Mission Impossible 2").a(Action).from(1999).starring(actor("Tom Cruise", Male, Film)).and(actor("Thandie Newton", Female, Unknown)).withThemes("Espionage"));
        add(aMovieCalled("Moulin Rouge").a(Genre.Musical).from(2001).starring(actor("Nicole Kidman", Female, Film)).and(actor("Ewan McGregor", Male, Film)).withThemes("Paris"));
        add(aMovieCalled("Never Say Never Again").a(Action).remake().from(1983).starring(actor("Sean Connery", Male, Film)).and(actor("Kim Basinger", Female, Film)).withThemes("Bond", "Espionage"));
        add(aMovieCalled("Ocean's 11").a(Thriller).from(1960).starring(actor("Frank Sinatra", Male, Music)).and(actor("Sammy Davis Jr", Male, Music)).withThemes("Espionage"));
        add(aMovieCalled("Ocean's 11").a(Comedy).remake().from(2001).starring(actor("George Clooney", Male, Unknown)).and(actor("Brad Pitt", Male, Film)).withThemes("Crime", "Caper", "Casino"));
        add(aMovieCalled("Snatch").a(Comedy).from(1999).starring(actor("Jason Stratham", Male, Unknown)).and(actor("Brad Pitt", Male, Film)).withThemes("Crime", "Diamonds"));
        add(aMovieCalled("Star Wars").a(SciFiAndFantasy).from(1977).starring(actor("Mark Hamill", Male, Unknown)).and(actor("Harrison Ford", Male, Film)).withThemes("War", "Rebellion", "Space"));
        add(aMovieCalled("The Accused").a(Comedy).from(1988).starring(actor("Jodie Foster", Female, Film)).and(actor("Bernie Coulson", Male, Unknown)).withThemes("Drugs", "Addiction"));
        add(aMovieCalled("The Shining").a(Horror).from(1980).starring(actor("Jack Nicholson", Male, Film)).and(actor("Shelley Duvall", Female, Film)).withThemes("Isolation", "Madness"));
        add(aMovieCalled("The World Is Not Enough").a(Action).from(1999).starring(actor("Pierce Brosnan", Male, Film)).and(actor("Robert Carlyle", Male, Film)).withThemes("Bond", "Espionage"));
        add(aMovieCalled("Thunderball").a(Action).from(1965).starring(actor("Sean Connery", Male, Film)).and(actor("Desmond Llewelyn", Male, Film)).withThemes("Bond", "Espionage"));
        add(aMovieCalled("Trainspotting").a(Comedy).from(1996).starring(actor("Ewan McGregor", Male, Film)).and(actor("Robert Carlyle", Male, Film)).withThemes("Drugs", "Addiction"));
    }};

    public Set<Movie> find(Matcher<Movie> filter) {
        Set<Movie> results = new HashSet<Movie>();
        for (Movie movie : COLLECTION) {
            if (filter.matches(movie)) results.add(movie);
        }
        return results;
    }
}
