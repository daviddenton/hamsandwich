package org.hamsandwich.example.combinableFilters.wiki;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamsandwich.example.combinableFilters.wiki.PersonMatchers.age;
import static org.hamsandwich.example.combinableFilters.wiki.PersonMatchers.name;

public class PersonTest {

    @Test
    public void detailsStoredProperly_v1() throws Exception {
        Person dave = new Person("Dave", 33);
        Person alan = new Person("Alan", 65);

        assertThePersonDetailsAgainst(dave, alan, "Dave", 33);
    }

    private void assertThePersonDetailsAgainst(Person actual, Person elderPerson, String expectedName, int expectedAge) {
        assertThat(actual.name, is(equalTo(expectedName)));
        assertThat(actual.age, is(equalTo(expectedAge)));
        assertThat(actual.age, is(lessThan(elderPerson.age)));
    }

    @Test
    public void detailsStoredProperly_v2() throws Exception {
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