package com.example.springtest.unitTest.mockito.helper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreetingSeparatedService {
    public void hello(String who) {
        String greeting = new GreetingGenerator(who).execute();
        log.info(greeting);
    }
}
