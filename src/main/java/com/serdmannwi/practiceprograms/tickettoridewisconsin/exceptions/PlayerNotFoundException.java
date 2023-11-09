package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
