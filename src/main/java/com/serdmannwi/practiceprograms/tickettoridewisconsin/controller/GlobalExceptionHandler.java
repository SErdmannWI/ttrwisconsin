package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.*;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.city.CityInitializationException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.city.CityNotFoundException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.event.EventNotFoundException;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**------------------------------------------ Player Controller Errors ------------------------------------------**/

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNOtFoundException(PlayerNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    @ExceptionHandler(MaxPlayersException.class)
    public ResponseEntity<ErrorResponse> handleMaxPlayersException(MaxPlayersException ex) {
        ErrorResponse errorResponse = new ErrorResponse(400, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    @ExceptionHandler(InvalidNumberOfPlayersException.class)
    public ResponseEntity<ErrorResponse> handleInvalidNumberOfPlayersException(InvalidNumberOfPlayersException ex) {
        ErrorResponse errorResponse = new ErrorResponse(400, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    @ExceptionHandler(AbilityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAbilityNotFoundException(AbilityNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    @ExceptionHandler(NoAvailableFreightStationException.class)
    public ResponseEntity<ErrorResponse> handleNoAvailableFreightStationException(NoAvailableFreightStationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(400, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    /**------------------------------------------- City Controller Errors -------------------------------------------**/
    @ExceptionHandler(CityInitializationException.class)
    public ResponseEntity<ErrorResponse> handleCityInitializationException(CityInitializationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(400, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCityNotFoundException(CityNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

    /**------------------------------------------- Event Controller Errors ------------------------------------------**/
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEventNotFoundException(EventNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }


}
