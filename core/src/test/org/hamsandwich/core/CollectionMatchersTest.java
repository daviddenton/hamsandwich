package org.hamsandwich.core;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CollectionMatchersTest {

    @Test
    public void filtersCorrectly() throws Exception {
        Iterator<Integer> resultsIt = CollectionMatchers.filter(new Integer[] {1, 2, 3, 4, 5}, is(equalTo(2))).iterator();
        assertThat(resultsIt.next(), is(equalTo(2)));
        assertThat(resultsIt.hasNext(), is(false));
    }
}
