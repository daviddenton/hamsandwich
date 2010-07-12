package org.hamsandwich.core;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BooleanAdaptingMatcherTest {

    @Test
    public void matchesResultCorrectly() throws Exception {
        assertThat(adapter().matchesSafely(true), is(true));
    }

    @HamSandwichFactory
    private BooleanAdaptingMatcher<Boolean> adapter() {
        return new BooleanAdaptingMatcher<Boolean>(true) {
            @Override
            public Boolean get(Boolean in) throws CannotAdaptException {
                return in;
            }
        };
    }
}