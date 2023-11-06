package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(String errorMessage) { super(errorMessage); }
}
