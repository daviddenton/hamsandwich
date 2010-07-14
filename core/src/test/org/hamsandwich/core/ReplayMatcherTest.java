package org.hamsandwich.core;

import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamsandwich.core.ReplayMatcher.on;
import static org.hamsandwich.core.ReplayMatcher.replayMatcher;
import static org.junit.Assert.assertThat;

public class ReplayMatcherTest {

    @Test
    @HamSandwichFactory
    public void matcherReplaysCapturedParameters() throws Exception {
        UUID unique = UUID.randomUUID();
        assertThat(new ReflectingBean(), replayMatcher(on(ReflectingBean.class).reflect(unique), is(equalTo((Object) unique))));
    }

    @Test
    @HamSandwichFactory
    public void proxyingMethodThatReturnsANumber() throws Exception {
        int value = 5;
        assertThat(new NumberBean(5), replayMatcher(on(NumberBean.class).value(), is(equalTo(value))));
    }

    @Test
    @HamSandwichFactory
    public void proxyingMethodThatReturnsAnObject() throws Exception {
        Object value = "asd";
        assertThat(new ObjectBean(value), replayMatcher(on(ObjectBean.class).value(), is(equalTo(value))));
    }

    @Test
    @HamSandwichFactory
    public void proxyingMethodThatReturnsABoolean() throws Exception {
        boolean value = true;
        assertThat(new BooleanBean(value), replayMatcher(on(BooleanBean.class).value(), is(equalTo(value))));
    }

    private class ReflectingBean {
        public Object reflect(Object o) {
            return o;
        }
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
