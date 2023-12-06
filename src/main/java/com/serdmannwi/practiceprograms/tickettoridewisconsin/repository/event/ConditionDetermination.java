package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event;

public enum ConditionDetermination {
    PLAYER_WITH_LOWEST_SCORE("playerLowestScore"),
    TEST("test"),
    CURRENT_PLAYER("currentPlayer");

    private final String value;
    ConditionDetermination(String value) { this.value = value; }

    public String getValue() { return value; }
}
