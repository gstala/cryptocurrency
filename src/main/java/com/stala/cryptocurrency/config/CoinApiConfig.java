package com.stala.cryptocurrency.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;

@Configuration
public class CoinApiConfig {

    @Value("${io.coinapi.rest.api-key}")
    private String coinApiKey;

    @Value("${io.coinapi.rest.base-url}")
    private String coinApiBaseUrl;

    @Bean("coinApiRestTemplate")
    public RestTemplate getCoinApiRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(coinApiBaseUrl)
                .setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .interceptors((request, body, execution) -> {
                    request.getHeaders().setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    request.getHeaders().set("X-CoinAPI-Key", coinApiKey);
                    return execution.execute(request, body);
                }).build();
    }
}
