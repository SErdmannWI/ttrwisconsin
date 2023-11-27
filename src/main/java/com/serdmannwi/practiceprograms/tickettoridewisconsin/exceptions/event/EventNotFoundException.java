package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.event;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
