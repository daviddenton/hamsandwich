package hamsandwich.core;

import java.lang.reflect.Method;

class StackInterrogatingNameResolver implements NameResolver {

    @Override
    public String resolveFor(Object o) {
        try {
            throw new Exception();
        } catch (Exception e) {
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                try {
                    Class<?> clientClass = Class.forName(stackTraceElement.getClassName());
                    for (Method m : clientClass.getDeclaredMethods()) {
                        if (m.getName().equals(stackTraceElement.getMethodName()) && m.getAnnotation(HamSandwichFactory.class) != null) {
                            return stackTraceElement.getMethodName();
                        }
                    }
                } catch (ClassNotFoundException e1) {
                    throw new RuntimeException("For some reason I couldn't find an existing class", e1);
                }
            }
            throw new RuntimeException("No name found");
        }
    }

}
