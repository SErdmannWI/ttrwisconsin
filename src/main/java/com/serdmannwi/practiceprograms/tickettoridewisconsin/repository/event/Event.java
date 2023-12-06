package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.event;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.eventcondition.EventCondition;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Parent class for all Events
 * All Events will be stored in the same table, organized by "Type"
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type", discriminatorType = DiscriminatorType.STRING)
public class Event {
    private String name;
    @Id
    private String eventId;
    private String cardDescription;
    private boolean isActive;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conditionId")
    private EventCondition eventCondition;

    public Event() {}

    public Event(String name, String eventId, String cardDescription, EventCondition eventCondition) {;
        this.name = name;
        this.eventId = eventId;
        this.cardDescription = cardDescription;
        this.isActive = false;
        this.eventCondition = eventCondition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public boolean isActive() { return isActive; }

    public void setActive(boolean isActive) { this.isActive = isActive; }

    public EventCondition getEventCondition() {
        return eventCondition;
    }

    public void setEventCondition(EventCondition eventCondition) {
        this.eventCondition = eventCondition;
    }
}
