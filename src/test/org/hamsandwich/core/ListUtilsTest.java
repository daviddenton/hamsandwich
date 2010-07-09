package org.hamsandwich.core;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void joinCreatesAUnifiedListFromSingleAndArrayOfObjects() {
        String first = "String" + 0;
        String second = "String" + 1;
        String third = "String" + 2;

        List<String> joinedStrings = ListUtils.join(first, new String[]{second, third});

        assertThat(joinedStrings.size(), is(equalTo(3)));
        assertThat(joinedStrings.get(0), is(equalTo(first)));
        assertThat(joinedStrings.get(1), is(equalTo(second)));
        assertThat(joinedStrings.get(2), is(equalTo(third)));
    }

}
