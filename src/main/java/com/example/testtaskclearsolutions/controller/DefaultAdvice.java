package com.example.testtaskclearsolutions.controller;

import java.time.LocalDateTime;

import com.example.testtaskclearsolutions.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<Response> handleException(Exception e) {
        Response response = new Response(LocalDateTime.now().toString(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

