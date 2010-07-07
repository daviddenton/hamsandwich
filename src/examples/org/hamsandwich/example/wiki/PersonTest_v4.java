package org.hamsandwich.example.wiki;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamsandwich.example.wiki.PersonMatchers.age;
import static org.hamsandwich.example.wiki.PersonMatchers.name;

public class PersonTest_v4 {

    @Test
    @Ignore
    public void detailsStoredProperly() throws Exception {
        Person dave = new Person("Dave", 33);
        Person alan = new Person("Alan", 65);

        assertThat(dave,
                allOf(
                        name(is(equalTo("Mark"))),
                        age(is(equalTo(33)), is(lessThan(alan.age)))
                )
        );
    }
}