package com.mercata.inventarium.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(404).body(new GenericErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(NotValidInputException.class)
    public ResponseEntity<Object> handleMissingValue(NotValidInputException ex) {
        return ResponseEntity.badRequest().body(new GenericErrorResponse(ex.getMessage()));
    }
    
}
