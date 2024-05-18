package com.example.springtest.jUnit.assertions.comparison;

import com.example.springtest.jUnit.assertions.comparison.annotation.TestToFail;
import com.example.springtest.jUnit.assertions.comparison.helper.Greeting;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class assertArrayEquals {
    @Test
    void test1() {
        int[] expected = {1,2,3};
        int[] actual = {1,2,3};
        assertArrayEquals(expected, actual);
    }

    @TestToFail
    void test2() {
        Object[] expected = {new Greeting("hello")};
        Object[] actual = {new Greeting("hello")};
        assertArrayEquals(expected, actual);
    }
}