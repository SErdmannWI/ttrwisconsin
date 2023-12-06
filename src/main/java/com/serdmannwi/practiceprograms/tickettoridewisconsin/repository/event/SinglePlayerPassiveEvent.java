package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.EventConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.eventcondition.EventCondition;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * SinglePlayerPassive Events are Events which affect only one Player and do not require further actions from the game
 * apart from determining which Player the Effect's Instructions are for.
 * Each Event needs:
 * String name, eventID, description
 * EventCondition (String id, description, effectId, ConditionDetermination(tells the game how to pick the Player)
 * PassiveEffect (String name, id, this Event's ID, effectInstruction)
 */

@Entity
@DiscriminatorValue(EventConstants.SINGLE_PLAYER_PASSIVE_EVENT)
public class SinglePlayerPassiveEvent extends Event {
    private String playerId;

    public SinglePlayerPassiveEvent() {}

    public SinglePlayerPassiveEvent(String name, String eventId, String cardDescription, EventCondition eventCondition) {
        super(name, eventId, cardDescription, eventCondition);
        this.playerId = "";
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }
}