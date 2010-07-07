package org.hamsandwich.core;

class StaticNameResolver implements NameResolver {
    private final String name;

    public StaticNameResolver(String name) {
        if (name == null) throw new IllegalArgumentException("Name was null");
        this.name = name;
    }

    @Override
    public String resolveFor(Object o) {
        return name;
    }
}
