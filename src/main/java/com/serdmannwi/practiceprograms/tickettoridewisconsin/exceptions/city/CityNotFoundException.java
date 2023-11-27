package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.city;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(String errorMessage) { super(errorMessage); }
}
