package org.hamsandwich.core;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class StackInterrogatingNameResolverTest {

    @Test
    public void retreivesFactoryMethodNameInStack() throws Exception {
        assertThat(theFactoryMethodToGetTheNameOf(this), is(equalTo("theFactoryMethodToGetTheNameOf")));
    }

    @HamSandwichFactory
    private String theFactoryMethodToGetTheNameOf(Object host) {
        return new StackInterrogatingNameResolver().resolveFor(host);
    }
}
