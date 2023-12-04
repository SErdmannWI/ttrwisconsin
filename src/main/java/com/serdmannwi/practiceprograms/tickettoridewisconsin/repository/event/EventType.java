package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event;

public enum EventType {
    SINGLEPLAYER_IMMEDIATE("singlePlayerImmediate"),
    SINGLEPLAYER_MULTITURN("singlePlayerMulti"),
    MULTIPLAYER_IMMEDIATE("multiplayerImmediate"),
    MULTIPLAYER_MULTITURN("multiplayerMulti");

    private final String value;

    EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
