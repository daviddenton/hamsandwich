package org.hamsandwich.core;

import org.junit.Test;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singleton;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamsandwich.hamcrest_to_junit_generics_mismatch.WildcardExtendsT.assertThat;

public class GenericUtilsTest {

    private class AClass<T> {
    }

    private class ASubClass<T> extends AClass<T> {
    }

    @Test
    public void findsGenericTypeArgumentsFromANonGenericClass() throws Exception {
        assertThat(GenericUtils.getGenerifiedClassesOf(new String()), is(equalTo(EMPTY_LIST)));
    }

    @Test
    public void findsGenericTypeArgumentsFromAGenericClass() throws Exception {
        assertThat(GenericUtils.getGenerifiedClassesOf(new AClass<String>()), is(equalTo(singleton(String.class))));
    }

    @Test
    public void findsGenericTypeArgumentsFromAGenericSubClass() throws Exception {
        assertThat(GenericUtils.getGenerifiedClassesOf(new ASubClass<String>()), is(equalTo(singleton(String.class))));
    }
}
