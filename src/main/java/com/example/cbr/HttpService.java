package com.example.cbr;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HttpService {
    private final WebClient client;
    private final CircuitBreakerRegistry registry;

    @CircuitBreaker(name = "cb", fallbackMethod = "falback")
    public Mono getStuff() {
        var cb = registry.circuitBreaker("cb");
//        cb.getEventPublisher()
//                .onStateTransition(event -> {
//            var type = event.getEventType();
//            var trans = event.getStateTransition();
//            System.out.println("transition handler!");
//            doEvent(event);
//            CircuitBreakerEvent.Type.STATE_TRANSITION.equals(CLOSED_TO_CLOSED)        });
//        cb.transitionToOpenState();
        var str = "hey";
        return doThing();
    }

    private Mono doThing() {
        return Mono.empty();
    }

    private Mono doString(String str) {
        return Mono.empty();
    }

    private void doEvent(CircuitBreakerOnStateTransitionEvent event) {
        var type = event.getEventType();
        var trans = event.getStateTransition();
        System.out.println("transition handler!");
    }
}
