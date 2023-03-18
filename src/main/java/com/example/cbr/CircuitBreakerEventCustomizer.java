package com.example.cbr;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class CircuitBreakerEventCustomizer {

    private final CircuitBreakerRegistry cbr;
    private final AlertService alertService;

    //TODO make this private and fix tests
    @PostConstruct
    public void postConstruct() {
        System.out.println("In the post construct method!");
        var cbs = this.cbr.getAllCircuitBreakers();
        for (CircuitBreaker cb : cbs) {
            cb.getEventPublisher()
                    .onStateTransition(event -> {
                        System.out.println("Handling a state transition event!");
                        switch (event.getStateTransition()) {
                            case CLOSED_TO_OPEN ->
                                alertService.sendAlert("This circuit breaker has opened!");
                            case HALF_OPEN_TO_CLOSED ->
                                alertService.sendAlert("This circuit breaker has closed!");
                        };
                    });
        }
    }

}
