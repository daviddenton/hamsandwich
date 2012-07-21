##Tutorial
###Installation
1. Download a binary package from here. The Zip distribution contains all of the Javadoc, dependencies and source code for the project.

or:

2. Check out the source code from here and build it manually. Prerequisites:

- Java 1.6
- Ant (we use v1.8)
- JUnit no dependencies version: junit-dep-<version>.jar - (we use v4.8.1)
Simply type ant or ant build at the command line.in the "core" folder

###Getting Started
If you're not familiar with the concept of Matchers and why they're ace, then it's probably a good idea to start with the Hamcrest Tutorial.

To integrate HamSandwich? into your project, the project Jar and contents of the Lib directory are required as dependencies. Note that as an older, incompatible version of Hamcrest (v1.1) is currently bundled with JUnit as standard, there may be classpath clashes if the full JUnit Jar is used.

Say we have an simple object:

```java
package org.hamsandwich.example.wiki;

public class Person {

    private final String name;
    public final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
}
```
... and a test for that class:

```java
package org.hamsandwich.example.wiki;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PersonTest {

    @Test
    public void detailsStoredProperly() throws Exception {
        Person dave = new Person("Dave", 33);
        Person alan = new Person("Alan", 65);

        assertThat(dave.getName(), is(equalTo("Dave")));
        assertThat(dave.age+10, is(equalTo(43)));
        assertThat(dave.age, is(lessThan(alan.age)));
    }
}
```

Each of the assertions needs to be declared on a separate line in the test. This approach does give us a nice stack trace if the test fails with the line number so you can easily determine which assertion failed, along with a message from the matcher indicating what went wrong.

The downside of this approach is that there is no way to easily group the various assertions into reusable modules, save for extracting a method which contains the 3 assertThat() calls, which would end up something like:

```java
package org.hamsandwich.example.wiki;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PersonTest {

    @Test
    public void detailsStoredProperly() throws Exception {
        Person dave = new Person("Dave", 33);
        Person alan = new Person("Alan", 65);

        assertThePersonDetailsAgainst(dave, alan, "Dave", 43);
    }

    private void assertThePersonDetailsAgainst(Person actual, Person elderPerson, String expectedName, int expectedAge) {
        assertThat(actual.getName(), is(equalTo(expectedName)));
        assertThat(actual.age+10, is(equalTo(expectedAge)));
        assertThat(actual.age, is(lessThan(elderPerson.age)));
    }
}
```

This is a quite ugly and ends up with all of the expected details being passed around, along with the object under test.

###Alternatively...
Using a HamSandwich? Matcher you can extract and group the common assertions, providing reuse in a Functional Style without hampering readability. To do so:

1. Decide on the common conditions that you want to assert on. These are typically fields, but you can build up less granular, more complex Matchers by composition. In the above example, we will use the name and age fields of the Person class.

2. Declare a factory method for each condition annotated with @HamSandwichFactory which will return a subclass of the AdaptingMatcher. This class requires the get() method to be implemented, which describes how to translate the input object (Person), into the output object (name -> String, age -> Integer). Alternatively, you can use a factory method to create a function replaying matcher (as done below for getName()). In this example we have grouped these methods onto a single utility class:

```java
package org.hamsandwich.example.wiki;

import org.hamcrest.Matcher;
import org.hamsandwich.core.AdaptingMatcher;
import org.hamsandwich.core.CannotAdaptException;
import org.hamsandwich.core.HamSandwichFactory;

import static org.hamsandwich.core.ReplayMatcher.on;
import static org.hamsandwich.core.ReplayMatcher.replayMatcher;

public class PersonMatchers {

    @HamSandwichFactory
    public static Matcher<Person> name(Matcher<String>... nameMatchers) {
        return replayMatcher(on(Person.class).getName(), nameMatchers);
    }

    @HamSandwichFactory
    public static Matcher<Person> ageInADecade(Matcher<? super Integer>... ageMatchers) {
        return new AdaptingMatcher<Person, Integer>(ageMatchers) {
            @Override
            public Integer get(Person in) throws CannotAdaptException {
                return in.age + 10;
            }
        };
    }
}
```

Note that the generics definition above involving super definitions Matcher<Integer> is required at the moment because of an issue with the version (1.3RC1) of Hamcrest that ships with HamSandwich?. This should be fixed soon in v1.3 as soon as it goes gold. In addition, the generics used above can be omitted from the signatures of these methods if required or desired. However, doing so will generate compiler warnings in your IDE.

3. You can now rewrite the test in order to use the above matchers:

```java
package org.hamsandwich.example.wiki;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamsandwich.example.wiki.PersonMatchers.ageInADecade;
import static org.hamsandwich.example.wiki.PersonMatchers.name;

public class PersonTest {

    @Test
    public void detailsStoredProperly() throws Exception {
        Person dave = new Person("Dave", 33);
        Person alan = new Person("Alan", 65);

        assertThat(dave,
                allOf(
                        name(is(equalTo("Dave"))),
                        ageInADecade(is(equalTo(43)), is(lessThan(alan.age)))
                )
        );
    }
}
```

We now have a single call to assertThat() passing in the dave instance, and have also combined the assertions for age into a single variable which itself can be extracted and reused.

###"But what about the knowing where the test failed?", I hear you cry...
Well, firstly, the original version of the test lets you know the exact line number of the assertion failure, but that is actually duplicate information - the message supplied by the matcher should give the actual reason that the test failed. Combining Matchers into a single assertThat() call removes the duplication, but retains the failure reason information by utilising the SelfDescribing feature of Hamcrest. The name of the condition (i.e. the name of the annotated Factory method) is used instead. So, if we change the test to deliberately fail:

```java
package org.hamsandwich.example.wiki;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamsandwich.example.wiki.PersonMatchers.ageInADecade;
import static org.hamsandwich.example.wiki.PersonMatchers.name;

public class PersonTest {

    @Test
    public void detailsStoredProperly() throws Exception {
        Person dave = new Person("Dave", 33);
        Person alan = new Person("Alan", 65);

        assertThat(dave,
                allOf(
                        name(is(equalTo("Mark"))),
                        ageInADecade(is(equalTo(53)), is(lessThan(alan.age)))
                )
        );
    }
}
```

... it now fails with the error message:

```java
java.lang.AssertionError: 
Expected: ([Person where name (is "Mark")] and [a Person where ageInADecade (is <53> and is a value less than <65>)])
     but: [Person where name (is "Mark")] is "Mark" was "Dave"
...
```

###And Finally...
It is also possible (nay, desirable!) to use these Matchers in production code as a filtering mechanic and there is an example of this usage included in the Zip distribution. An interesting benefit of the Hamcrest SelfDescribing functionality is that these Matchers generate human-readable descriptions from the describeTo() and toString() methods.

The library will also hugely benefit from the introduction of Closures in Java7 (whenever that turns up), as the implementation of the get() method on AdaptingMatcher can be replaced by a Closure passed into the constructor, thus cleaning up a lot of code in the Factory methods.

Have a play. Let us know what you think.

:)
