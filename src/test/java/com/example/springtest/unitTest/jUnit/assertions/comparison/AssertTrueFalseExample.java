package com.example.springtest.unitTest.jUnit.assertions.comparison;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertTrueFalseExample {

    @Test
    void checkTrue() {
        assertTrue(true);
    }

    @Test
    void checkTrueWithSupplier() {
        assertTrue(() -> {
            return true;
        });
    }

    @Test
    void checkFalse() {
        assertFalse(false);
    }

}