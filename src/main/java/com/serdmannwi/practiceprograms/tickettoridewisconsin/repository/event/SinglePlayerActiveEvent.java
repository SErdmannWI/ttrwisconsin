package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.EventConstants;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.eventcondition.EventCondition;
import jakarta.persistence.*;

/**
 * SinglePlayerActiveEvents are Events which affect only one Player but require a further actions from a Player in order
 * to resolve the Event.
 * Each Event needs:
 * String name, eventId, description, actionId, playerId
 * EventCondition (String id, description, effectId, ConditionDetermination(tells the game how to pick the Player)
 * ActiveEffect (String name, id, this Event's ID, effectInstruction)
 */
@Entity
@DiscriminatorValue(EventConstants.SINGLE_PLAYER_ACTIVE_EVENT)
public class SinglePlayerActiveEvent extends Event {
    private String playerId;
    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EventCondition eventCondition;

    public SinglePlayerActiveEvent() {}

    public SinglePlayerActiveEvent(String name, String eventId, String cardDescription, EventCondition eventCondition){
        super(name, eventId, cardDescription, eventCondition);
        this.playerId = "";
        this.eventCondition = eventCondition;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public EventCondition getEventCondition() {
        return eventCondition;
    }

    public void setEventCondition(EventCondition eventCondition) {
        this.eventCondition = eventCondition;
    }

    @Override
    public String toString() {
        return "SinglePlayerActiveEvent{" +
            "playerId='" + playerId + '\'' +
            ", eventCondition=" + eventCondition +
            '}';
    }
}
