package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.ResponseEntity;
@ControllerAdvice

public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
        protected ResponseEntity <String> entityNotFoundHandler(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<String> illegalArgumentHandler(IllegalArgumentException e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdConflictException.class)
    protected ResponseEntity<String> idConflictHandler(IdConflictException e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<String> nullHandler(NullPointerException e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<String> illegalStateHandler(IllegalStateException e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

}
