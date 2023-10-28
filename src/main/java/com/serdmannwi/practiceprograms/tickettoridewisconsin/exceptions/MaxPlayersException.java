package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions;

public class MaxPlayersException extends Exception{
    public MaxPlayersException(String errorMessage) {
        super(errorMessage);
    }
}
