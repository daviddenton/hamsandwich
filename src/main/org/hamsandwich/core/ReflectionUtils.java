package org.hamsandwich.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class ReflectionUtils {

    public static List<Class> getGenerifiedClassesOf(Object o) {
        ParameterizedType genericSuperclass = (ParameterizedType) o.getClass().getGenericSuperclass();
        List<Class> classes = new ArrayList<Class>();
        for (Type type : genericSuperclass.getActualTypeArguments()) {
            Class targetClass = type instanceof ParameterizedType ? (Class) ((ParameterizedType) type).getRawType() : (Class) type;
            classes.add(targetClass);
        }
        return classes;
    }
}
