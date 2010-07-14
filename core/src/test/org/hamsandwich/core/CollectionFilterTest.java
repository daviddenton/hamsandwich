package org.hamsandwich.core;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CollectionFilterTest {

    @Test
    public void selectsCorrectly() throws Exception {
        Iterator<Integer> resultsIt = CollectionFilter.select(new Integer[]{1, 2, 3, 4, 5}, is(equalTo(2))).iterator();
        assertThat(resultsIt.next(), is(equalTo(2)));
        assertThat(resultsIt.hasNext(), is(false));
    }

    @Test
    public void removesCorrectly() throws Exception {
        Iterator<Integer> resultsIt = CollectionFilter.remove(new Integer[]{1, 2, 3, 4, 5}, is(equalTo(2))).iterator();
        assertThat(resultsIt.next(), is(equalTo(1)));
        assertThat(resultsIt.next(), is(equalTo(3)));
        assertThat(resultsIt.next(), is(equalTo(4)));
        assertThat(resultsIt.next(), is(equalTo(5)));
        assertThat(resultsIt.hasNext(), is(false));
    }
}
