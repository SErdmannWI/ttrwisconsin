package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.constants.EventConstants;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue(EventConstants.SINGLE_PLAYER_ACTIVE_EVENT)
public class SinglePlayerActiveEvent extends Event {
    private String playerId;
    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EventCondition eventCondition;
    private String actionId; //ID of Action affected by failing to meet the EventCondition

    public SinglePlayerActiveEvent() {}

    public SinglePlayerActiveEvent(String name, String eventId, String cardDescription, String actionId,
                                   EventCondition eventCondition){
        super(eventCondition.getConditionId(), eventCondition.getConditionDescription(), eventCondition.getEffectId(),
            name, eventId, cardDescription);
        this.actionId = actionId;
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

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    @Override
    public String toString() {
        return "SinglePlayerActiveEvent{" +
            "playerId='" + playerId + '\'' +
            ", eventCondition=" + eventCondition +
            ", actionId='" + actionId + '\'' +
            '}';
    }
}
