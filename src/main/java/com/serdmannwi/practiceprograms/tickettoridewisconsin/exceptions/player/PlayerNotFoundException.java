package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
