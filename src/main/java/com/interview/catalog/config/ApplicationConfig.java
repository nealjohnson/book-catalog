package com.interview.catalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;

@Configuration
public class ApplicationConfig {
    @Value("${services.timeout}")
    private long timeOut;

    private RestServiceInterceptor restServiceInterceptor;

    @Autowired
    public ApplicationConfig(RestServiceInterceptor restServiceInterceptor) {
        this.restServiceInterceptor = restServiceInterceptor;
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(timeOut));
        RestTemplate restClient = restTemplateBuilder.build();
        restClient.setInterceptors(Collections.singletonList(restServiceInterceptor));
        return restClient;
    }
}
