package com.example.cbr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Configurations {
    @Bean(name = "client")
    WebClient client() {
        return WebClient.builder()
                .build();
    }

    @Bean(name = "alertClient")
    WebClient alertClient() {
        return WebClient.builder()
                .build();
    }

}
