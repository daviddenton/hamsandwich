package org.hamsandwich.core;

class GenerifiedClassEntityNameResolver implements NameResolver {
    @Override
    public String resolveFor(Object object) {
        return "a " + ReflectionUtils.getGenerifiedClassesOf(object).get(0).getSimpleName();
    }
}
