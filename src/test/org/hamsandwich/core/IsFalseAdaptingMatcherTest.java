package org.hamsandwich.core;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IsFalseAdaptingMatcherTest {

    @Test
    public void matchesFalseCorrectly() throws Exception {
        assertThat(adapter().matchesSafely(false), is(true));
    }

    @HamSandwichFactory
    private IsFalseAdaptingMatcher<Boolean> adapter() {
        return new IsFalseAdaptingMatcher<Boolean>() {
            @Override
            public Boolean get(Boolean in) throws CannotAdaptException {
                return in;
            }
        };
    }
}