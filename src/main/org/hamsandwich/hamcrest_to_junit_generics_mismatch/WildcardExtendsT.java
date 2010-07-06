package org.hamsandwich.hamcrest_to_junit_generics_mismatch;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

/**
 * Workaround factory class for generics mismatch between Hamcrest and JUnit introduced in Hamcrest 1.2.
 */
public class WildcardExtendsT {

    private WildcardExtendsT() {
    }

    /**
     * <p/>
     * Asserts that <code>actual</code> satisfies the condition specified by
     * <code>matcher</code>. If not, an {@link AssertionError} is thrown with
     * information about the matcher and failing value. Example:
     * <p/>
     * <pre>
     *   assertThat(0, is(1)); // fails:
     *     // failure message:
     *     // expected: is &lt;1&gt;
     *     // got value: &lt;0&gt;
     *   assertThat(0, is(not(1))) // passes
     * </pre>
     *
     * @param actual  the computed value being compared
     * @param matcher an expression, built of {@link org.hamcrest.Matcher}s, specifying allowed
     *                values
     * @see org.hamcrest.CoreMatchers
     * @see org.junit.matchers.JUnitMatchers
     */
    public static <T> void assertThat(T actual, Matcher<? extends T> matcher) {
        MatcherAssert.assertThat(actual, (Matcher<T>) matcher);
    }
}
