package com.serdmannwi.practiceprograms.tickettoridewisconsin.exceptions;

public class InvalidNumberOfPlayersException extends Exception{
    public InvalidNumberOfPlayersException(String errorMessage) {
        super(errorMessage);
    }
}
