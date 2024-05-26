package com.example.springtest.unitTest.mockito;

import com.example.springtest.unitTest.mockito.helper.GreetingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * packageName : com.example.springtest.unitTest.mockito
 * fileName : StubbingReturnExampleTest
 * author : taeil
 * date : 5/26/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 5/26/24        taeil                   최초생성
 */
public class StubbingReturnExampleTest {

    @Test
    void test1() {
        GreetingService mocked = mock();

        when(mocked.greeting("world")).thenReturn("hi world");

        assertEquals("hi world", mocked.greeting("world"));
        assertEquals("hi world", mocked.greeting("world"));
    }

    @Test
    void test2() {
        GreetingService mocked = mock();

        when(mocked.greeting("world"))
                .thenReturn(
                        "hello world",
                        "hoi world",
                            "hi world"
                );

        assertEquals("hello world", mocked.greeting("world"));
        assertEquals("hoi world", mocked.greeting("world"));
        assertEquals("hi world", mocked.greeting("world"));
        assertEquals("hi world", mocked.greeting("world"));
    }

    @Test
    void test3() {
        GreetingService mocked = mock();

        when(mocked.greeting("world"))
                .thenReturn("hoi world")
                .thenReturn("hi world");

        assertEquals("hoi world", mocked.greeting("world"));
        assertEquals("hi world", mocked.greeting("world"));
    }
}