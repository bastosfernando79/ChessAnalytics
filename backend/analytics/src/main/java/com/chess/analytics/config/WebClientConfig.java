package com.chess.analytics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // O @Bean ensina o Spring a criar e disponibilizar essa ferramenta
    // para qualquer outra classe que precisar (como o nosso Service)
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}