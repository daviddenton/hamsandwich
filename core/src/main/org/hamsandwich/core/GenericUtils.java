package org.hamsandwich.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GenericUtils {
    public static List<Class> getGenerifiedClassesOf(Object o) {
      Type genericSuperclass = o.getClass().getGenericSuperclass();
        if(genericSuperclass instanceof Class) {
            return Collections.EMPTY_LIST;
        }
        if(genericSuperclass == Object.class) {
            // more than one
            ParameterizedType generifiedAdaptingMatcherClass = (ParameterizedType) genericSuperclass;
            List<Class> classes = new ArrayList<Class>();
            for (Type type : generifiedAdaptingMatcherClass.getActualTypeArguments()) {
                Class targetClass = type instanceof ParameterizedType ? (Class) ((ParameterizedType) type).getRawType() : (Class) type;
                classes.add(targetClass);
            }
            return classes;

        } else {
            // more than one level of generics
            ParameterizedType generifiedAdaptingMatcherClass = (ParameterizedType) genericSuperclass;
            List<Class> classes = new ArrayList<Class>();
            for (Type type : generifiedAdaptingMatcherClass.getActualTypeArguments()) {
                Class targetClass = type instanceof ParameterizedType ? (Class) ((ParameterizedType) type).getRawType() : (Class) type;
                classes.add(targetClass);
            }
            return classes;
        }
//        if(typeVariables.length == 0) return Collections.EMPTY_LIST;
////        if(genericSuperclass instanceof Class) return Collections.EMPTY_LIST;
//
//        ParameterizedType generifiedAdaptingMatcherClass = (ParameterizedType) genericSuperclass;
//        List<Class> classes = new ArrayList<Class>();
//        for (Type type : generifiedAdaptingMatcherClass.getActualTypeArguments()) {
//            Class targetClass = type instanceof ParameterizedType ? (Class) ((ParameterizedType) type).getRawType() : (Class) type;
//            classes.add(targetClass);
//        }
//        return classes;
    }
}
