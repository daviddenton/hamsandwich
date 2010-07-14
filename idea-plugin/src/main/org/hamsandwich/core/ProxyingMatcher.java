package org.hamsandwich.core;

import net.sf.cglib.proxy.InvocationHandler;
import org.hamcrest.Matcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyingMatcher<I, O> extends AdaptingMatcher<I, O> {

    public static <I, O> Matcher<I> proxy(O notUsed, Matcher<O>... valueMatchers) {
        return new ProxyingMatcher<I, O>(valueMatchers);
    }

    private ProxyingMatcher(Matcher<? super O>... valueMatchers) {
        super(valueMatchers);
    }

    public static <T> T on(Class<T> clazz) {
        return ConcreteClassProxyFactory.INSTANCE.proxyFor(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                new ThreadLocal<InvocationReplayer>().set(new InvocationReplayer(method, objects));
                return null;
            }
        }, clazz);
    }

    @Override
    public O get(I in) throws CannotAdaptException {
        try {
            return (O) new ThreadLocal<InvocationReplayer>().get().replay(in);
        } catch (IllegalAccessException e) {
            throw new CannotAdaptException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new CannotAdaptException(e.getMessage());
        }
    }
}
