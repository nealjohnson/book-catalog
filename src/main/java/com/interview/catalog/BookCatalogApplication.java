package com.interview.catalog;

import com.interview.catalog.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class BookCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookCatalogApplication.class, args);
    }

}
