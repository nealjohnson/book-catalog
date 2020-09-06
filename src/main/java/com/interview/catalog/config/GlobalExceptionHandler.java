package com.interview.catalog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class GlobalExceptionHandler {

   private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(final Exception exception) {
        // We are throwing the ResponseStatusException from one place only.
        if(exception instanceof ResponseStatusException){
            final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
            LOGGER.error(message, exception);
            return new ResponseEntity(message, ((ResponseStatusException) exception).getStatus());
        }else {
            final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
            LOGGER.error(message, exception);
            return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}