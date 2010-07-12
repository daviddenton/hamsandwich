package org.hamsandwich.core;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IsEqualAdaptingMatcherTest {

    @Test
    public void matchesEqualCorrectly() throws Exception {
        assertThat(adapter("HELLO").matchesSafely("HELLO"), is(true));
    }

    @Test
    public void mismatchesCorrectly() throws Exception {
        assertThat(adapter("HELLO").matchesSafely("NOTHELLO"), is(false));
    }

    @HamSandwichFactory
    private IsEqualAdaptingMatcher<String, String> adapter(String testValue) {
        return new IsEqualAdaptingMatcher<String, String>(testValue) {
            @Override
            public String get(String in) throws CannotAdaptException {
                return in;
            }
        };
    }
}