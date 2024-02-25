package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler class that extends {@link ResponseEntityExceptionHandler}.
 * This class uses {@link ControllerAdvice} to provide centralized exception handling across all {@code @RequestMapping} methods.
 */
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    /**
     * Handles {@link EntityNotFoundException} by returning a response entity with a NOT FOUND status.
     *
     * @param e the encountered {@link EntityNotFoundException}
     * @return a {@link ResponseEntity} with the exception message and HTTP status NOT FOUND
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<String> entityNotFoundHandler(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link IllegalArgumentException} by returning a response entity with a BAD REQUEST status.
     *
     * @param e the encountered {@link IllegalArgumentException}
     * @return a {@link ResponseEntity} with the exception message and HTTP status BAD REQUEST
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<String> illegalArgumentHandler(IllegalArgumentException e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link IdConflictException} by returning a response entity with a CONFLICT status.
     *
     * @param e the encountered {@link IdConflictException}
     * @return a {@link ResponseEntity} with the exception message and HTTP status CONFLICT
     */
    @ExceptionHandler(IdConflictException.class)
    protected ResponseEntity<String> idConflictHandler(IdConflictException e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.CONFLICT);
    }

}
