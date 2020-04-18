package com.stala.cryptocurrency.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.text.MessageFormat.format;

@Slf4j
@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpStatusCodeException.class)
    protected ResponseEntity<String> handleHttpStatusCodeException(HttpStatusCodeException exception) {
        log.error(format("HttpStatusCodeException occur with code: {0} and message: {1}", exception.getStatusCode(), exception.getMessage()));

        return new ResponseEntity<>("Rest integration error", exception.getStatusCode());
    }

}
