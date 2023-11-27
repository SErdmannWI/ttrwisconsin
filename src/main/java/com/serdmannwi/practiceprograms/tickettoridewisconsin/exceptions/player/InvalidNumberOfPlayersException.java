package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions.player;

public class InvalidNumberOfPlayersException extends RuntimeException{
    public InvalidNumberOfPlayersException(String errorMessage) {
        super(errorMessage);
    }
}
