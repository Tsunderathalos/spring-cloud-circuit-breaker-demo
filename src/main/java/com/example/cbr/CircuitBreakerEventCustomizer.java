package com.example.cbr;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static io.github.resilience4j.circuitbreaker.CircuitBreaker.StateTransition.CLOSED_TO_OPEN;

@Component
@RequiredArgsConstructor
public class CircuitBreakerEventCustomizer {

private final CircuitBreakerRegistry cbr;
private final AlertService alertService;
    @PostConstruct
    private void postConstruct() {
        System.out.println("In the post construct method!");
        var cbs = this.cbr.getAllCircuitBreakers();
        for(CircuitBreaker cb : cbs) {
            cb.getEventPublisher()
                .onStateTransition(event -> {
                    System.out.println("Handling a state transition event!");
                    if(event.getStateTransition().equals(CLOSED_TO_OPEN)) {
                        System.out.println("Opened!");
                        alertService.sendAlert("This circuit breaker be opened!");
                    }
                });
        }
    }

}
