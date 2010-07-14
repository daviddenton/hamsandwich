package org.hamsandwich.core;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamsandwich.core.FunctionMatcher.functionMatcher;
import static org.hamsandwich.core.FunctionMatcher.on;
import static org.junit.Assert.assertThat;

public class FunctionMatcherTest {

    @Test
    @HamSandwichFactory
    public void proxyingMethodThatReturnsANumber() throws Exception {
        int value = 5;
        assertThat(new NumberBean(5), functionMatcher(on(NumberBean.class).value(), is(equalTo(value))));
    }

    @Test
    @HamSandwichFactory
    public void proxyingMethodThatReturnsAnObject() throws Exception {
        Object value = "asd";
        assertThat(new ObjectBean(value), functionMatcher(on(ObjectBean.class).value(), is(equalTo(value))));
    }

    @Test
    @HamSandwichFactory
    public void proxyingMethodThatReturnsABoolean() throws Exception {
        boolean value = true;
        assertThat(new BooleanBean(value), functionMatcher(on(BooleanBean.class).value(), is(equalTo(value))));
    }

    private class NumberBean {
        private final int anInt;

        public NumberBean(int number) {
            anInt = number;
        }

        public int value() {
            return anInt;
        }
    }

    private class BooleanBean {
        private final boolean aBoolean;

        public BooleanBean(boolean aBoolean) {
            this.aBoolean = aBoolean;
        }

        public boolean value() {
            return aBoolean;
        }
    }

    private class ObjectBean {
        private final Object anObject;

        private ObjectBean(Object anObject) {
            this.anObject = anObject;
        }

        public Object value() {
            return anObject;
        }
    }
}
