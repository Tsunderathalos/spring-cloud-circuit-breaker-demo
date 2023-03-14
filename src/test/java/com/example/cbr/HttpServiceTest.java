package com.example.cbr;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HttpServiceTest {

    @Autowired
    private HttpService target;

    @Autowired
    private CircuitBreakerRegistry cbr;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStuff() {
        // arrange
        var cb = cbr.circuitBreaker("cb");

        // act
        var stuff = target.getStuff();
        cb.transitionToOpenState();
        cb.transitionToClosedState();
        cb.transitionToOpenState();

        // assert
        System.out.println("We got stuff!");
    }
}