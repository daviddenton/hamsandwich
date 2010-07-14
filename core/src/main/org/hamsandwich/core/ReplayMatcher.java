package org.hamsandwich.core;

import net.sf.cglib.proxy.InvocationHandler;
import org.hamcrest.Matcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * An adapting Hamcrest matcher which replays a function call on the target object and matches the resulting output value.
 * Can be created with an optional name, otherwise uses the simple class name of the input type for description purposes.
 * Use the static factory methods to create instances, passing the result of the function on().method() call
 *
 * @param <I> The input type.
 * @param <O> The output type.
 */
public class ReplayMatcher<I, O> extends AdaptingMatcher<I, O> {
    private static final ThreadLocal<InvocationReplayer> INVOCATION_REPLAYER = new ThreadLocal<InvocationReplayer>();
    private final InvocationReplayer invocationReplayer;

    /**
     * Creates a replaying function matcher, using the simple class name of the input type for description purposes.
     *
     * @param functionResult pass in the function to be replayed here. Use the static on() method to record this function.
     * @param valueMatchers  the delegated matchers for the output of the adaption.
     * @param <I>            The input type.
     * @param <O>            The output type.
     * @return the FunctionMatcher instance
     */
    public static <I, O> Matcher<I> replayMatcher(O functionResult, Matcher<O>... valueMatchers) {
        try {
            assertThatFunctionHasBeenRecorded();
            return new ReplayMatcher<I, O>(INVOCATION_REPLAYER.get(), valueMatchers);
        } finally {
            INVOCATION_REPLAYER.remove();
        }
    }

    /**
     * Creates a replaying function matcher, using the passed entity name for description purposes.
     *
     * @param entityName     the assigned name of the matched entity, used for description purposes.
     * @param functionResult pass in the function to be replayed here. Use the static on() method to record this function.
     * @param valueMatchers  the delegated matchers for the output of the adaption.
     * @param <I>            The input type.
     * @param <O>            The output type.
     * @return the FunctionMatcher instance
     */
    public static <I, O> Matcher<I> replayMatcher(String entityName, O functionResult, Matcher<O>... valueMatchers) {
        try {
            assertThatFunctionHasBeenRecorded();
            return new ReplayMatcher<I, O>(entityName, INVOCATION_REPLAYER.get(), valueMatchers);
        } finally {
            INVOCATION_REPLAYER.remove();
        }
    }

    private static void assertThatFunctionHasBeenRecorded() {
        if (INVOCATION_REPLAYER.get() == null)
            throw new IllegalArgumentException("Function was not recorded. Did you pass use the on(Class) factory method to create the functionResult?");
    }

    private ReplayMatcher(InvocationReplayer invocationReplayer, Matcher<? super O>... valueMatchers) {
        super(valueMatchers);
        this.invocationReplayer = invocationReplayer;
    }

    private ReplayMatcher(String entityName, InvocationReplayer invocationReplayer, Matcher<? super O>... valueMatchers) {
        super(entityName, valueMatchers);
        this.invocationReplayer = invocationReplayer;
    }

    public static <T> T on(Class<T> clazz) {
        return ConcreteClassProxyFactory.INSTANCE.proxyFor(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                INVOCATION_REPLAYER.set(new InvocationReplayer(method, objects));
                if (method.getReturnType().getName().equals("boolean")) return false;
                if (method.getReturnType().isPrimitive()) return 0;
                return null;
            }
        }, clazz);
    }

    @Override
    public O get(I in) throws CannotAdaptException {
        try {
            return (O) invocationReplayer.replay(in);
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
