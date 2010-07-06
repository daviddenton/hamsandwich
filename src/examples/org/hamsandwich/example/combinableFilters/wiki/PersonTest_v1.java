package org.hamsandwich.example.combinableFilters.wiki;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PersonTest_v1 {

    @Test
    public void detailsStoredProperly() throws Exception {
        Person dave = new Person("Dave", 33);
        Person alan = new Person("Alan", 65);

        assertThat(dave.name, is(equalTo("Dave")));
        assertThat(dave.age, is(equalTo(33)));
        assertThat(dave.age, is(lessThan(alan.age)));
    }
}