package org.hamsandwich.core;

import net.sf.cglib.proxy.InvocationHandler;
import org.hamcrest.Matcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * An adapting Hamcrest matcher which replays a function call and matches the resulting output value.
 * Can be created with an optional name, otherwise uses the simple class name of the input type for description purposes.
 * Use the static factory methods to create instances, passing the result of the function on().method() call
 *
 * @param <I> The input type.
 * @param <O> The output type.
 */
public class FunctionMatcher<I, O> extends AdaptingMatcher<I, O> {

    /**
     * Creates a replaying function matcher, using the simple class name of the input type for description purposes.
     *
     * @param notUsed       pass in the function to be replayed here. Use the static on() method to record this function.
     * @param valueMatchers the delegated matchers for the output of the adaption.
     * @param <I>           The input type.
     * @param <O>           The output type.
     * @return the FunctionMatcher instance
     */
    public static <I, O> Matcher<I> functionMatcher(O notUsed, Matcher<O>... valueMatchers) {
        return new FunctionMatcher<I, O>(valueMatchers);
    }

    /**
     * Creates a replaying function matcher, using the passed entity name for description purposes.
     *
     * @param entityName    the assigned name of the matched entity, used for description purposes.
     * @param notUsed       pass in the function to be replayed here. Use the static on() method to record this function.
     * @param valueMatchers the delegated matchers for the output of the adaption.
     * @param <I>           The input type.
     * @param <O>           The output type.
     * @return the FunctionMatcher instance
     */
    public static <I, O> Matcher<I> functionMatcher(String entityName, O notUsed, Matcher<O>... valueMatchers) {
        return new FunctionMatcher<I, O>(valueMatchers);
    }

    private FunctionMatcher(Matcher<? super O>... valueMatchers) {
        super(valueMatchers);
    }

    private FunctionMatcher(String entityName, Matcher<? super O>... valueMatchers) {
        super(entityName, valueMatchers);
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

    private static class InvocationReplayer {
        private final Method method;
        private final Object[] params;

        public InvocationReplayer(Method method, Object[] params) {
            this.method = method;
            this.params = params;
        }

        public Object replay(Object in) throws InvocationTargetException, IllegalAccessException {
            return method.invoke(in, params);
        }
    }
}
