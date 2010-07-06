package hamsandwich.example.combinableFilters.wiki;

import org.junit.Test;

import static hamsandwich.example.combinableFilters.wiki.PersonMatchers.age;
import static hamsandwich.example.combinableFilters.wiki.PersonMatchers.name;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PersonTest_v3 {

    @Test
    public void detailsStoredProperly() throws Exception {
        Person dave = new Person("Dave", 33);
        Person alan = new Person("Alan", 65);

        assertThat(dave,
                allOf(
                        name(is(equalTo("Dave"))),
                        age(is(equalTo(33)), is(lessThan(alan.age)))
                )
        );
    }
}