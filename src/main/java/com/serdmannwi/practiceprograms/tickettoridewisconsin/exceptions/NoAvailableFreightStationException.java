package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions;

public class NoAvailableFreightStationException extends Exception{
    public NoAvailableFreightStationException(String errorMessage) {
        super(errorMessage);
    }
}
