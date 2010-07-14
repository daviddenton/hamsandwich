package org.hamsandwich.core;

import net.sf.cglib.core.CodeGenerationException;
import net.sf.cglib.core.DefaultNamingPolicy;
import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.core.Predicate;
import net.sf.cglib.proxy.*;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


/**
 * Creates a proxy for a concrete class
 * NB: Shamelessly "inspired" by JMock's ClassImposteriser
 */
class ConcreteClassProxyFactory {

    static final ConcreteClassProxyFactory INSTANCE = new ConcreteClassProxyFactory();

    private ConcreteClassProxyFactory() {
    }

    <T> T proxyFor(final InvocationHandler invocationHandler, final Class<T> concreteClass, Class... interfaces) {
        if (!canImposterise(concreteClass)) throw new IllegalArgumentException("Can't imposterize ");

        try {
            setConstructorsAccessible(concreteClass, true);
            Class<T> proxyClass = createProxyClass((Class) concreteClass, interfaces);
            Object o = createProxy(proxyClass, invocationHandler);
            System.out.println(o.getClass());
            return (T) ((Class) concreteClass).cast(o);
        } finally {
            setConstructorsAccessible(concreteClass, false);
        }
    }

    private static final NamingPolicy NAMING_POLICY_THAT_ALLOWS_IMPOSTERISATION_OF_CLASSES_IN_SIGNED_PACKAGES = new DefaultNamingPolicy() {
        public String getClassName(String prefix, String source, Object key, Predicate names) {
            return "org.niceassert.codegen." + super.getClassName(prefix, source, key, names);
        }
    };

    private static final CallbackFilter IGNORE_BRIDGE_METHODS = new CallbackFilter() {
        public int accept(Method method) {
            return method.isBridge() ? 1 : 0;
        }
    };

    private boolean canImposterise(final Class targetClass) {
        return !targetClass.isPrimitive() && !Modifier.isFinal(targetClass.getModifiers()) && !toStringMethodIsFinal(targetClass);
    }

    private boolean toStringMethodIsFinal(Class type) {
        try {
            return Modifier.isFinal(type.getMethod("toString").getModifiers());
        } catch (SecurityException e) {
            throw new IllegalStateException("not allowed to reflect on toString method", e);
        } catch (NoSuchMethodException e) {
            throw new Error("no public toString method found", e);
        }
    }

    private void setConstructorsAccessible(Class targetClass, boolean accessible) {
        for (Constructor<?> constructor : targetClass.getDeclaredConstructors()) {
            constructor.setAccessible(accessible);
        }
    }

    private static <T> Class<T> createProxyClass(Class targetClass, Class... interfaces) {
        Class<?> targetType = targetClassFor(targetClass);

        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(targetType.getClassLoader());
        enhancer.setUseFactory(true);
        enhancer.setSuperclass(targetType);
        enhancer.setInterfaces(interfaces);
        enhancer.setCallbackTypes(new Class[]{InvocationHandler.class});
        enhancer.setCallbackFilter(IGNORE_BRIDGE_METHODS);
        if (targetType.getSigners() != null) {
            enhancer.setNamingPolicy(NAMING_POLICY_THAT_ALLOWS_IMPOSTERISATION_OF_CLASSES_IN_SIGNED_PACKAGES);
        }

        try {
            return enhancer.createClass();
        }
        catch (CodeGenerationException e) {
            throw new IllegalArgumentException("could not imposterise " + targetType, e);
        }
    }

    private static Class targetClassFor(Class targetClass) {
        return targetClass != Object.class ? targetClass : ClassWithSuperclassToWorkAroundCglibBug.class;
    }

    private Object createProxy(Class proxyClass, final InvocationHandler invocationHandler) {
        Factory proxy = (Factory) new ObjenesisStd().newInstance(proxyClass);
        proxy.setCallbacks(new Callback[]{invocationHandler});
        return proxy;
    }

    private static class ClassWithSuperclassToWorkAroundCglibBug {
    }

}
