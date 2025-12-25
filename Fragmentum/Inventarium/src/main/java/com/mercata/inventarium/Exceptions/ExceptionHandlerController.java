package com.mercata.inventarium.Exceptions;

import org.springframework.dao.DuplicateKeyException;
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

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbidden(ForbiddenException ex) {
        return ResponseEntity.status(403).body(new GenericErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKey(DuplicateKeyException ex) {
        return ResponseEntity.status(409).body(new GenericErrorResponse(ex.getMessage()));
    }
    
}
