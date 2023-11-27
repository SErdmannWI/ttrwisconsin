package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player;

public class MaxPlayersException extends RuntimeException{
    public MaxPlayersException(String errorMessage) {
        super(errorMessage);
    }
}
