package com.example.springtest.privateMethodTest;

import com.example.springtest.unitTest.mockito.helper.GreetingGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodObjectTest {
    @Test
    void test1() {
        String who = "world";
        GreetingGenerator greetingGenerator = new GreetingGenerator(who);

        String expected = "hello " + who;
        assertEquals(expected, greetingGenerator.execute());
    }
}
