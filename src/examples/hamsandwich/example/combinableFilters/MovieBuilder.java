package hamsandwich.example.combinableFilters;

import java.util.Set;

public class MovieBuilder {
    private String title;
    private Genre genre;
    private boolean remake;
    private int year;
    private Actor leadingActor;
    private Actor supportingActor;
    private Set<String> themes;

    public MovieBuilder(String title) {
        this.title = title;
    }

    public MovieBuilder a(Genre genre) {
        this.genre = genre;
        return this;
    }

    public MovieBuilder from(int year) {
        this.year = year;
        return this;
    }

    public MovieBuilder remake() {
        this.remake = true;
        return this;
    }

    public MovieBuilder starring(Actor leadingActor) {
        this.leadingActor = leadingActor;
        return this;
    }

    public MovieBuilder and(Actor supportingActor) {
        this.supportingActor = supportingActor;
        return this;
    }

    public Movie withThemes(String... themes) {
        return new Movie(title, year, genre, leadingActor, supportingActor, remake, themes);
    }

    public static MovieBuilder aMovieCalled(String title) {
        return new MovieBuilder(title);
    }
}