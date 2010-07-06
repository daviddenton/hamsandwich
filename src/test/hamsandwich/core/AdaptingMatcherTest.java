package hamsandwich.core;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AdaptingMatcherTest {

    @HamSandwichFactory
    private static Matcher<Boolean> condition(Matcher<Boolean> valueMatcher) {
        return new AdaptingMatcher<Boolean, Boolean>(valueMatcher) {
            @Override
            public Boolean get(Boolean in) throws CannotAdaptException {
                return in;
            }
        };
    }

    @Test
    public void succeedsWhenNestedMatcherSucceeds() {
        assertTrue(condition((Matcher<Boolean>) is(true)).matches(true));
    }

    @Test
    public void failsToMatchWhenNestedMatcherFails() {
        assertFalse(condition((Matcher<Boolean>) is(true)).matches(false));
    }

    @Test
    public void describesExpectedCondition() {
        StringDescription description = new StringDescription();
        condition((Matcher<Boolean>) is(true)).describeTo(description);
        assertThat(description.toString(), is(equalTo("[a Boolean where condition (is <true>)]")));
    }

    @Test
    public void describesMismatchOfValidClass() {
        StringDescription description = new StringDescription();
        condition((Matcher<Boolean>) is(true)).describeMismatch(false, description);
        assertThat(description.toString(), is(equalTo("is <true> was <false>")));
    }

    @Test
    public void describesMismatchOfMismatchedClass() {
        StringDescription description = new StringDescription();
        condition((Matcher<Boolean>) is(true)).describeMismatch("this is not a boolean", description);
        assertThat(description.toString(), is(equalTo("was \"this is not a boolean\"")));
    }

    @Test
    public void usesGetterMethodToRetreiveValue() {
        String testString = "aStringToGetTheLengthOf";
        assertTrue(matchTheLengthOfThisString(testString, testString.length()));
        assertFalse(matchTheLengthOfThisString(testString, 1));
    }

    @HamSandwichFactory
    private boolean matchTheLengthOfThisString(String testString, int checkedLength) {
        Matcher<String> theLengthOfTheString = new AdaptingMatcher<String, Integer>(is(equalTo(checkedLength))) {
            @Override
            public Integer get(String in) throws CannotAdaptException {
                return in.length();
            }
        };
        return theLengthOfTheString.matches(testString);
    }
}
