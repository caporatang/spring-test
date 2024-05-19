package com.example.springtest.jUnit.assumptions;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.*;

public class AssumeTrueFalseExampleTest {

    @Test
    void checkTrue() {
        assumeTrue(true);
    }

    @Test
    void checkTrueWithSupplier() {
        assumeTrue(() -> {
            return true;
        });
    }

    @Test
    void checkFalse() {
        assumeFalse(false);
    }

}