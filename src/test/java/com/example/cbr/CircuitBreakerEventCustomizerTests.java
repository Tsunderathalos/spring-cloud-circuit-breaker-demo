package com.example.cbr;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CircuitBreakerEventCustomizerTests {

    private CircuitBreakerRegistry circuitBreakerRegistry;

    private AlertService mockAlertService;

    private CircuitBreakerEventCustomizer customizer;
    private final String circuitBreakerName = "cb";

    @BeforeEach
    void setUp() {
        this.mockAlertService = Mockito.mock(AlertService.class);
        this.circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
        this.circuitBreakerRegistry.circuitBreaker(this.circuitBreakerName);
        this.customizer = new CircuitBreakerEventCustomizer(this.circuitBreakerRegistry, this.mockAlertService);
        this.customizer.postConstruct();
    }

    @Test
    void circuitBreaker_sendsAlertOnOpenAndClose() {
        // arrange
        var breaker = circuitBreakerRegistry.circuitBreaker(circuitBreakerName);

        // act
        breaker.transitionToOpenState();
        breaker.transitionToHalfOpenState();
        breaker.transitionToClosedState();

        // assert
        Mockito.verify(mockAlertService, Mockito.times(1)).sendAlert("This circuit breaker has opened!");
        Mockito.verify(mockAlertService, Mockito.times(1)).sendAlert("This circuit breaker has closed!");
    }
}
