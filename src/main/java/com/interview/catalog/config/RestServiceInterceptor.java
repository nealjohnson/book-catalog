package com.interview.catalog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RestServiceInterceptor implements ClientHttpRequestInterceptor {

   private final Logger LOGGER = Logger.getLogger(RestServiceInterceptor.class.getName());

    @Value("${spring.application.name}")
    private String applicationName;
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        LOGGER.log(Level.INFO,httpRequest.getURI().toString());
        httpRequest.getHeaders().add("serviceName",applicationName);
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);
        return response;
    }
}
