package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.EventConstants;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue(EventConstants.SINGLE_PLAYER_ACTIVE_EVENT)
public class SinglePlayerActiveEvent extends Event{
    private String playerId;
    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private EventCondition eventCondition;
    private String actionId; //ID of Action affected by failing to meet the EventCondition

    public SinglePlayerActiveEvent() {}

    public SinglePlayerActiveEvent(String name, String eventId, String cardDescription, String actionId) {
        super(name, eventId, cardDescription);
        this.actionId = actionId;
        this.playerId = "";
        this.eventCondition = null;
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

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }


}
