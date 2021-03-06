package org.hamsandwich.example.wiki;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamsandwich.example.wiki.PersonMatchers.ageInADecade;
import static org.hamsandwich.example.wiki.PersonMatchers.name;

public class PersonTest_v3 {

    @Test
    public void detailsStoredProperly() throws Exception {
        Person dave = new Person("Dave", 33);
        Person alan = new Person("Alan", 65);

        assertThat(dave,
                allOf(
                        name(is(equalTo("Dave"))),
                        ageInADecade(is(equalTo(43)), is(lessThan(alan.age)))
                )
        );
    }
}
