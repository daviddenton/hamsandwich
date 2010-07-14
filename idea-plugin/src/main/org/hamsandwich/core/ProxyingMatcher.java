package org.hamsandwich.core;

import net.sf.cglib.proxy.InvocationHandler;
import org.hamcrest.Matcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyingMatcher<I, O> extends AdaptingMatcher<I, O> {
    private final O proxy;

    public ProxyingMatcher(O proxy, Matcher<? super O>... valueMatchers) {
        super(valueMatchers);
        this.proxy = proxy;
    }

    // new ProxyMatcher(on(Blah.class).getValue(), valueMatchers)
    @Override
    public O get(I in) throws CannotAdaptException {
        RecordingInvocationHandler handler = new RecordingInvocationHandler(); // not this one
        try {
            return (O) handler.replay(in);
        } catch (IllegalAccessException e) {
            throw new CannotAdaptException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new CannotAdaptException(e.getMessage());
        }
    }

    public static <T> T on(Class<T> clazz) {
        return ConcreteClassProxyFactory.INSTANCE.proxyFor(new RecordingInvocationHandler(), clazz);
    }

    private static class RecordingInvocationHandler<T, O> implements InvocationHandler {
        private Method method;
        private Object[] objects;

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            this.method = method;
            this.objects = objects;
            return null;
        }

        public O replay(T in) throws InvocationTargetException, IllegalAccessException {
            return (O) method.invoke(in, objects);
        }
    }
}
