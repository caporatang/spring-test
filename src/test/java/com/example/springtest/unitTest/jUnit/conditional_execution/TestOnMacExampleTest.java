package com.example.springtest.unitTest.jUnit.conditional_execution;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOnMacExampleTest {

    @TestOnMac
    void test1() {
        assertTrue(true);
    }
}