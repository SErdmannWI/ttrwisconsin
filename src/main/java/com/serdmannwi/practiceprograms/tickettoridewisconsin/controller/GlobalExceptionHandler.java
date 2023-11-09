package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.ErrorResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNOtFoundException(PlayerNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }
}
