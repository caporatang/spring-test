package com.example.springtest.privateMethodTest;

import com.example.springtest.unitTest.mockito.helper.GreetingService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrivateTestReflectionTest {
    @SneakyThrows
    @Test
    void test1() {
        GreetingService greetingService = new GreetingService();

        Method privateMethod = greetingService.getClass()
                .getDeclaredMethod("prepareGreeting", String.class);

        privateMethod.setAccessible(true);

        String greeting = (String)privateMethod.invoke(
                greetingService, "world"
        );

        assertEquals("hello world", greeting);
    }
}
