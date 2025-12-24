package com.mercata.inventarium.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mercata.inventarium.DTO.GenericErrorResponse;
import com.mercata.inventarium.Errors.MissingValueException;
import com.mercata.inventarium.Errors.NotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(404).body(new GenericErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(MissingValueException.class)
    public ResponseEntity<Object> handleMissingValue(MissingValueException ex) {
        return ResponseEntity.badRequest().body(new GenericErrorResponse(ex.getMessage()));
    }
    
}
