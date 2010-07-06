package org.hamsandwich.core;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IsTrueAdaptingMatcherTest {

    @Test
    public void matchesTrueCorrectly() throws Exception {
        assertThat(matcher().matchesSafely(true), (Matcher<Object>) is(true));
    }

    @HamSandwichFactory
    private IsTrueAdaptingMatcher<Boolean> matcher() {
        return new IsTrueAdaptingMatcher<Boolean>() {
            @Override
            public Boolean get(Boolean in) throws CannotAdaptException {
                return in;
            }
        };
    }
}