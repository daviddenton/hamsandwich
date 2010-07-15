package org.hamsandwich.example.wiki;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PersonTest_v2 {

    @Test
    public void detailsStoredProperly() throws Exception {
        Person dave = new Person("Dave", 33);
        Person alan = new Person("Alan", 65);

        assertThePersonDetailsAgainst(dave, alan, "Dave", 43);
    }

    private void assertThePersonDetailsAgainst(Person actual, Person elderPerson, String expectedName, int expectedAge) {
        assertThat(actual.getName(), is(equalTo(expectedName)));
        assertThat(actual.age+10, is(equalTo(expectedAge)));
        assertThat(actual.age, is(lessThan(elderPerson.age)));
    }

}