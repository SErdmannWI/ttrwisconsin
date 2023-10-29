package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import jakarta.persistence.*;

@Entity
public class Event {
    private String name;
    @Id
    private String eventId;
    private String cardDescription;
    private String effectDescription;
    private String resolutionMessage;
    private String expirationMessage;
    private EventType eventType;
    private boolean isActive;
    private boolean hasExpired;
    private int turnsRemaining;
    private int eventDuration;

    public Event() {}

    public Event(String name, String eventId, String cardDescription, String effectDescription, String resolutionMessage,
                 String expirationMessage, EventType eventType, boolean isActive, boolean hasExpired, int turnsRemaining,
                 int eventDuration) {
        this.name = name;
        this.eventId = eventId;
        this.cardDescription = cardDescription;
        this.effectDescription = effectDescription;
        this.resolutionMessage = resolutionMessage;
        this.expirationMessage = expirationMessage;
        this.eventType = eventType;
        this.isActive = isActive;
        this.hasExpired = hasExpired;
        this.turnsRemaining = turnsRemaining;
        this.eventDuration = eventDuration;
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

    public String getEffectDescription() {
        return effectDescription;
    }

    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    public String getResolutionMessage() {
        return resolutionMessage;
    }

    public void setResolutionMessage(String resolutionMessage) {
        this.resolutionMessage = resolutionMessage;
    }

    public String getExpirationMessage() {
        return expirationMessage;
    }

    public void setExpirationMessage(String expirationMessage) {
        this.expirationMessage = expirationMessage;
    }

    public EventType getEventType() {
        return eventType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean hasExpired() {
        return hasExpired;
    }

    public void setHasExpired(boolean hasExpired) {
        this.hasExpired = hasExpired;
    }

    public int getTurnsRemaining() {
        return turnsRemaining;
    }

    public void setTurnsRemaining(int turnsRemaining) {
        this.turnsRemaining = turnsRemaining;
    }

    public int getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(int eventDuration) {
        this.eventDuration = eventDuration;
    }
}
