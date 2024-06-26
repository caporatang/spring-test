package com.example.springtest.testGrammar.sliceTest.domain;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface UserR2dbcRepository extends R2dbcRepository<UserEntity, Long> {
    Mono<UserEntity> findByName(String name);
}
