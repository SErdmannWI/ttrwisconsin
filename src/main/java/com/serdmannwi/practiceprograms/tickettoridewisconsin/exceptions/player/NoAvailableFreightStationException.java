package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player;

public class NoAvailableFreightStationException extends RuntimeException{
    public NoAvailableFreightStationException(String errorMessage) {
        super(errorMessage);
    }
}
