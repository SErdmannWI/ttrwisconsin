package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions;

public class PlayerNotFoundException extends Exception{
    public PlayerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
