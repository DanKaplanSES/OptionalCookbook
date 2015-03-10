package com.sleepeasysoftware.optionalcookbook;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Daniel Kaplan on behalf of Sleep Easy Software.
 */
public class Usage {

    private Map<String, String> map = new HashMap<>();

    public void creatingOptional() {
        Optional<String> empty = Optional.empty();  //this is an alternative to using "null"
        Optional<String> something = Optional.of("hi");  //this is an alternative to using "hi"
    }

    /**
     * This method will find something, or maybe it won't.  Notice how the API of the code says it's using Optional.
     * This means you can tell, without even looking at this documentation, that you can pass in Optional.empty() or a
     * real value.  It also implies that you can't pass in "null".
     * <p>
     * If we wrote a maybeFindSomething that used nulls instead, you would HAVE to take time to investigate if
     * you are allowed to pass in nulls or nulls are returned.  Here's an alternative signature of maybeFindSomething
     * that takes nulls:
     * <code>
     * <pre>
     *     public String maybeFindSomething(String key);
     * </pre>
     * </code>
     * Pretty typical, but the problem is you don't know if the return value can be null or the parameter you pass in
     * can be null without investigating further.
     *
     * @param maybeKey A key to use to look up data.  This is an Optional so you are allowed to pass in Optional.empty()
     * @return If a value is found, this will return Optional.of(value), otherwise it will return Optional.empty()
     */
    public Optional<String> maybeFindSomething(Optional<String> maybeKey) {
        if (maybeKey.isPresent()) {
            return Optional.ofNullable(map.get(maybeKey.get()));   //if the map.get call returns "null", return Optional.empty(), otherwise, return Optional.of(map.get(key))
        } else {
            return Optional.empty();
        }
    }

    public void usingOptional() {

        /**
         * Now we're going to show an example of calling some code that returns Optional
         */
        map.put("foo", "bar");
        Optional<String> maybeFoo = maybeFindSomething(Optional.of("foo"));   //we put "foo -> bar" in the map, so this will return a value
        if (maybeFoo.isPresent()) {
            System.out.println("I found a value for \"foo\": " + maybeFoo.get());   //this will print something
        }

        Optional<String> maybeBaz = maybeFindSomething(Optional.of("baz"));   //we did not put "baz -> ..." in the map, so this will return an Optional.empty()
        if (maybeBaz.isPresent()) {
            System.out.println("I found a value for \"baz\": " + maybeBaz.get());   //we will not enter this if statement
        }

        //the maybeFindSomething API tells us, with self documenting code, that we can pass in an Optional.  This means we are allowed to pass in Optional.empty()
        Optional<String> maybeEmpty = maybeFindSomething(Optional.empty());   //we did not put "baz -> ..." in the map, so this will return an Optional.empty()
        if (maybeEmpty.isPresent()) {
            System.out.println("I found a value for Optional.empty(): " + maybeEmpty.get());   //we will not enter this if statement
        }

    }

    public static void main(String[] args) {
        new Usage().usingOptional();
    }

}
