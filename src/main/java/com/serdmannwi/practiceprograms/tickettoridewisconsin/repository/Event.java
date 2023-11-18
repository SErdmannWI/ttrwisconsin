package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import jakarta.persistence.*;

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

    public Event() {}

    public Event(String name, String eventId, String cardDescription) {
        this.name = name;
        this.eventId = eventId;
        this.cardDescription = cardDescription;
        this.isActive = false;
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
}
