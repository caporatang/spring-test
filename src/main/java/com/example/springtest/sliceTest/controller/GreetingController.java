package com.example.springtest.sliceTest.controller;

import com.example.springtest.sliceTest.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/greeting")
public class GreetingController {
    private final GreetingService greetingService;

    public Mono<String> greeting(
            @RequestParam String who
    ) {
        return greetingService.greetingMono(who);
    }
}
