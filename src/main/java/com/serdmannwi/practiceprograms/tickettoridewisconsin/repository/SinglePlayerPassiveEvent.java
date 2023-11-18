package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.EventConstants;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(EventConstants.SINGLE_PLAYER_PASSIVE_EVENT)
public class SinglePlayerPassiveEvent extends Event{
    private String playerId;

    public SinglePlayerPassiveEvent() {}

    public SinglePlayerPassiveEvent(String name, String eventId, String cardDescription) {
        super(name, eventId, cardDescription);
        this.playerId = "";
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }
}
