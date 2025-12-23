package com.mercata.inventarium.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mercata.inventarium.Errors.NotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
    
}
