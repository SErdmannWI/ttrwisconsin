package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions;

public class CityInitializationException extends RuntimeException {
    public CityInitializationException(String errorMessage, Throwable cause) { super(errorMessage, cause); }
}
