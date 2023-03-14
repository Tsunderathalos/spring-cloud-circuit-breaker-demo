package com.example.cbr;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final WebClient alertClient;

    public void sendAlert(String message) {
        System.out.println("sending alert! message = " + message + " client: "  + alertClient.toString());
    }
}
