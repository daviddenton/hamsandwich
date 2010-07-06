package hamsandwich.example.combinableFilters;

public class Actor {
    public final String name;
    public final Origin origin;
    public final Gender gender;

    private Actor(String name, Gender gender, Origin origin) {
        this.name = name;
        this.origin = origin;
        this.gender = gender;
    }

    public static Actor actor(String name, Gender gender, Origin origin) {
        return new Actor(name, gender, origin);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", origin=" + origin +
                ", gender=" + gender +
                '}';
    }
}

