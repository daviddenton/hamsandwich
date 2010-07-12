package org.hamsandwich.example.combinableFilters;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class Movie {
    public final String title;
    public final int year;
    public final Genre genre;
    public final boolean remake;
    public final Actor leadingActor;
    public final Actor supportingActor;
    public final Set<String> themes;

    public Movie(String title, int year, Genre genre, Actor leadingActor, Actor supportingActor, boolean remake, String... themes) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.remake = remake;
        this.leadingActor = leadingActor;
        this.supportingActor = supportingActor;
        this.themes = new HashSet<String>(asList(themes));
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", genre=" + genre +
                ", remake=" + remake +
                ", leadingActor=" + leadingActor +
                ", supportingActor=" + supportingActor +
                ", themes=" + themes +
                '}';
    }
}
