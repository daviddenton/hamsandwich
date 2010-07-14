package org.hamsandwich.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class InvocationReplayer {
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
