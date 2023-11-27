package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player;

public class AbilityNotFoundException extends RuntimeException{
    public AbilityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
